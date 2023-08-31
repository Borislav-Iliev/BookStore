package com.example.bookstore.model.dto.user;

import com.example.bookstore.model.validation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserUpdateDto {
    @NotBlank(message = "Username must be provided!")
    @Size(min = 2, max = 25, message = "Username must be between 2 and 25 characters!")
    @UniqueUsername(message = "Username has already been taken!")
    private String username;

    @NotBlank(message = "First name must be provided!")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters!")
    private String firstName;

    @NotBlank(message = "Last name must be provided!")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters!")
    private String lastName;

    @NotBlank(message = "Display name must be provided!")
    @Size(min = 2, max = 100, message = "Display name must be between 2 and 100 characters!")
    private String displayName;

    @NotNull(message = "Date of birth must be provided!")
    @PastOrPresent(message = "Date of birth cannot be in the future!")
    private LocalDate dateOfBirth;

    public UserUpdateDto() {
    }

    public UserUpdateDto(String username, String firstName, String lastName, String displayName, @NotNull(message = "Date of birth must be provided!") LocalDate dateOfBirth) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public UserUpdateDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserUpdateDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserUpdateDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public UserUpdateDto setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public UserUpdateDto setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }
}
