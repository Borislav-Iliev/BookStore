package com.example.bookstore.service;

import com.example.bookstore.model.entity.User;
import com.example.bookstore.model.enums.UserRoleEnum;
import com.example.bookstore.model.user.BookStoreUserDetails;
import com.example.bookstore.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class BookStoreUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public BookStoreUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .map(this::mapToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + "not found!"));
    }

    private UserDetails mapToUserDetails(User user) {
        return new BookStoreUserDetails(
                user.getUsername(),
                user.getPassword(),
                List.of(this.mapToGrantedAuthority(user.getRole())),
                user.getFirstName(),
                user.getLastName()
        );
    }

    private GrantedAuthority mapToGrantedAuthority(UserRoleEnum userRoleEnum) {
        return new SimpleGrantedAuthority("ROLE_" + userRoleEnum.name());
    }
}
