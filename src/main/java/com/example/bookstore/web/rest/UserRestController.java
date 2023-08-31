package com.example.bookstore.web.rest;

import com.example.bookstore.config.UserAuthenticationProvider;
import com.example.bookstore.model.dto.user.UserLoginDto;
import com.example.bookstore.model.dto.user.UserRegisterDto;
import com.example.bookstore.model.dto.user.UserDto;
import com.example.bookstore.model.dto.user.UserUpdateDto;
import com.example.bookstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity
                .ok(this.userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity
                .ok(this.userService.getUserById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserUpdateDto userUpdateDto, @PathVariable Long id) {
        UserDto updatedUser = this.userService.updateUser(id, userUpdateDto);

        return ResponseEntity
                .ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
        UserDto deletedUser = this.userService.deleteUserById(id);

        return ResponseEntity
                .ok(deletedUser);
    }
}
