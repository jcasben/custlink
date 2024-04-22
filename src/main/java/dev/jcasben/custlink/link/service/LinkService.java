package dev.jcasben.custlink.link.service;

import dev.jcasben.custlink.link.model.Link;
import dev.jcasben.custlink.link.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepository linkRepository;

    public Link createLink(Link link) {
        return linkRepository.save(link);
    }

    public Link findLinkByOwnerAndName(String owner, String name) {
        return linkRepository.findByOwnerAndName(owner, name);
    }

    public List<Link> findAllLinksByOwner(String owner) {
        return linkRepository.findAllByOwner(owner);
    }

    public Link updateLink(String owner, String name, Link link) {
        return linkRepository.updateLinkByOwnerAndName(owner, name, link);
    }

    public void deleteLinkByOwnerAndName(String owner, String name) {
        linkRepository.deleteByOwnerAndName(owner, name);
    }
}
