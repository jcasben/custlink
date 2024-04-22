package dev.jcasben.custlink.link.repository;

import dev.jcasben.custlink.link.model.Link;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends MongoRepository<Link, String> {
    List<Link> findAllByOwner(String owner);
    Link findByOwnerAndName(String owner, String name);
    void deleteByOwnerAndName(String owner, String name);
}
