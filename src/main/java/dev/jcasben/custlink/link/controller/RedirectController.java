package dev.jcasben.custlink.link.controller;

import dev.jcasben.custlink.link.model.Link;
import dev.jcasben.custlink.link.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class RedirectController {

    private final LinkService linkService;

    @GetMapping("{owner}/{name}")
    public ResponseEntity<Void> redirect(
            @PathVariable("owner") String owner,
            @PathVariable("name") String name
    ) {
        Link link = linkService.findLinkByOwnerAndName(owner, name);
        System.out.println(link.toString());
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(link.getUrl()))
                .build();
    }
}