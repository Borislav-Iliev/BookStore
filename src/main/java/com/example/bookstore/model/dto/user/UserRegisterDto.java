package com.example.bookstore.model.dto.user;

import com.example.bookstore.model.validation.FieldMatch;
import com.example.bookstore.model.validation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords must match"
)
public class UserRegisterDto {

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

    @NotBlank(message = "Password must be provided!")
    @Size(min = 2, max = 25, message = "Password must be between 2 and 25 characters!")
    private String password;

    @NotBlank(message = "Password must be provided!")
    @Size(min = 2, max = 25, message = "Password must be between 2 and 25 characters!")
    private String confirmPassword;

    @NotNull(message = "Date of birth must be provided!")
    @PastOrPresent(message = "Date of birth cannot be in the future!")
    private LocalDate dateOfBirth;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String username, String firstName, String lastName, String displayName, String password, String confirmPassword, LocalDate dateOfBirth) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public UserRegisterDto setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public UserRegisterDto setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }
}
