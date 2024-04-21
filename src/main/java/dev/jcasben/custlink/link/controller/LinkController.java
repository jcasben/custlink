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
    private final JwtService jwtService;

    @PostMapping("/createLink")
    public ResponseEntity<Link> createLink(
            @RequestBody Link link,
            @RequestHeader("Authorization") String token
    ) {
        link.setOwner(jwtService.getUsernameFromRawToken(token));
        Link newLink = linkService.createLink(link);
        return new ResponseEntity<>(newLink, HttpStatus.CREATED);
    }

    @GetMapping("/links")
    public ResponseEntity<List<Link>> getAllLinksByOwner(
            @RequestHeader("Authorization") String token
    ) {
        List<Link> links = linkService.findAllLinksByOwner(
                jwtService.getUsernameFromRawToken(token)
        );
        return new ResponseEntity<>(links, HttpStatus.OK);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<Link> updateLink(
            @RequestBody Link link,
            @RequestHeader("Authorization") String token
    ) {
        link.setOwner(jwtService.getUsernameFromRawToken(token));
        Link updatedLink = linkService.updateLink(link);
        return new ResponseEntity<>(updatedLink, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> deleteLink(
            @PathVariable("name") String name,
            @RequestHeader("Authorization") String token
    ) {
        linkService.deleteLinkByOwnerAndName(
                jwtService.getUsernameFromRawToken(token),
                name
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }
}