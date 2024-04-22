package dev.jcasben.custlink.user.service;

import dev.jcasben.custlink.user.model.User;
import dev.jcasben.custlink.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    public User updateUser(String username, User user) {
        return userRepository.updateUserByUsername(username, user);
    }

    public void deleteUserByUsername(String username) {
        userRepository.deleteUserByUsername(username);
    }

    public boolean existsUserByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }
}
