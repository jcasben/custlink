package dev.jcasben.custlink.user.controller;

import dev.jcasben.custlink.jwt.JwtService;
import dev.jcasben.custlink.user.model.User;
import dev.jcasben.custlink.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

//    @PutMapping("update")
//    public ResponseEntity<User> updateUser(
//            @RequestBody User user,
//            @RequestHeader("Authorization") String token
//    ) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        User newUser = userService.updateUser(
//                jwtService.getUsernameFromRawToken(token),
//                user
//        );
//        return new ResponseEntity<>(newUser, HttpStatus.OK);
//    }

    @DeleteMapping("delete")
    public void deleteUser(@RequestHeader("Authorization") String token) {
        userService.deleteUserByUsername(
                jwtService.getUsernameFromRawToken(token)
        );
    }
}
