package dev.jcasben.custlink.link.repository;

import dev.jcasben.custlink.link.model.Link;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LinkRepository extends MongoRepository<Link, String> {
    Optional<Link> findAllByOwner(String owner);
}
