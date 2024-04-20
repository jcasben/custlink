package dev.jcasben.custlink.user.repository;

import dev.jcasben.custlink.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    boolean existsUserByEmail(String email);
    boolean existsUserByUsername(String username);
    void deleteByUsername(String username);
}
