package dev.jcasben.custlink.link.controller;

import dev.jcasben.custlink.jwt.JwtService;
import dev.jcasben.custlink.link.model.Link;
import dev.jcasben.custlink.link.service.LinkService;
import dev.jcasben.custlink.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/link")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("{owner}/createLink")
    public ResponseEntity<Link> createLink(
            @PathVariable("owner") String owner,
            @RequestBody Link link
    ) {
        if (userService.existsUserByUsername(owner)) {
            link.setOwner(owner);
            Link newLink = linkService.createLink(link);
            return new ResponseEntity<>(newLink, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/links")
    public ResponseEntity<List<Link>> getAllLinksByOwner(@RequestHeader("Authorization") String token) {
        String owner = jwtService.getUsernameFromToken(token.substring(7));
        List<Link> links = linkService.findAllLinksByOwner(owner);
        return new ResponseEntity<>(links, HttpStatus.OK);
    }

    @PutMapping("{owner}/update/{name}")
    public ResponseEntity<Link> updateLink(
            @PathVariable("owner") String owner,
            @RequestBody Link link
    ) {
        if (userService.existsUserByUsername(owner)) {
            link.setOwner(owner);
            Link updatedLink = linkService.updateLink(link);
            return new ResponseEntity<>(updatedLink, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{owner}/delete/{name}")
    public ResponseEntity<?> deleteLink(
            @PathVariable("owner") String owner,
            @PathVariable("name") String name
    ) {
        linkService.deleteLinkByOwnerAndName(owner, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}