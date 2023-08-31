package com.example.bookstore.web.rest;

import com.example.bookstore.config.UserAuthenticationProvider;
import com.example.bookstore.model.dto.user.UserDto;
import com.example.bookstore.model.dto.user.UserLoginDto;
import com.example.bookstore.model.dto.user.UserRegisterDto;
import com.example.bookstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class AuthenticationRestController {

    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    public AuthenticationRestController(UserService userService, UserAuthenticationProvider userAuthenticationProvider) {
        this.userService = userService;
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        UserDto userDto = this.userService.register(userRegisterDto);
        userDto.setToken(this.userAuthenticationProvider.createToken(userRegisterDto.getUsername()));

        return ResponseEntity
                .created(URI.create("/api/users" + userDto.getId()))
                .body(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody @Valid UserLoginDto userLoginDto) {
        UserDto userDto = this.userService.login(userLoginDto);
        userDto.setToken(this.userAuthenticationProvider.createToken(userLoginDto.getUsername()));

        return ResponseEntity
                .ok(userDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        this.userService.logout();

        return ResponseEntity
                .ok("Successfully logged out!");
    }
}
