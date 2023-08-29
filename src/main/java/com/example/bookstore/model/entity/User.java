package com.example.bookstore.model.entity;

import com.example.bookstore.model.enums.UserRoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
	private String username;

    @Column(name = "first_name", nullable = false)
	private String firstName;

    @Column(name = "last_name", nullable = false)
	private String lastName;

    @Column(name = "display_name", nullable = false)
	private String displayName;

    @Column(nullable = false)
	private String password;

    @Column(name = "date_of_birth", nullable = false)
	private LocalDate dateOfBirth;

    @Column(nullable = false)
	private UserRoleEnum role;

    public User() {
    }

    public User(String username, String firstName, String lastName, String displayName, String password, LocalDate dateOfBirth, UserRoleEnum role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public User setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public User setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public User setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
