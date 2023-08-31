package com.example.bookstore.model.dto.user;

import com.example.bookstore.model.enums.UserRoleEnum;

import java.time.LocalDate;

public class UserDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String displayName;
    private LocalDate dateOfBirth;
    private UserRoleEnum role;
    private String token;

    public UserDto() {
    }

    public UserDto(Long id, String username, String firstName, String lastName, String displayName, LocalDate dateOfBirth, UserRoleEnum role, String token) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public UserDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public UserDto setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public UserDto setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserDto setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }

    public String getToken() {
        return token;
    }

    public UserDto setToken(String token) {
        this.token = token;
        return this;
    }
}
