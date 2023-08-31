package com.example.bookstore.service;

import com.example.bookstore.model.dto.user.UserDto;
import com.example.bookstore.model.dto.user.UserLoginDto;
import com.example.bookstore.model.dto.user.UserRegisterDto;
import com.example.bookstore.model.dto.user.UserUpdateDto;
import com.example.bookstore.model.entity.User;
import com.example.bookstore.model.enums.UserRoleEnum;
import com.example.bookstore.model.exception.ObjectNotFoundException;
import com.example.bookstore.model.mappings.UserMapper;
import com.example.bookstore.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public UserDto register(UserRegisterDto userRegisterDto) {
        this.userRepository
                .findByUsername(userRegisterDto.getUsername())
                .orElseThrow(() -> new ObjectNotFoundException("User not found!"));

        User user = UserMapper.mapFromUserRegisterDtoToUserEntity(userRegisterDto);

        user.setPassword(this.passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setRole(UserRoleEnum.USER);

        User newUser = this.userRepository.save(user);

        return UserMapper.mapFromUserEntityToUserDto(newUser);
    }

    public UserDto login(UserLoginDto userLoginDto) {
        User user = this.userRepository
                .findByUsername(userLoginDto.getUsername())
                .orElseThrow(() -> new ObjectNotFoundException("Invalid username or password!"));

       if (this.passwordEncoder.matches(CharBuffer.wrap(userLoginDto.getPassword()), user.getPassword())) {
           return UserMapper.mapFromUserEntityToUserDto(user);
       }

       throw new ObjectNotFoundException("Invalid username or password");
    }

    public void logout() {
        SecurityContextHolder.clearContext();
    }

    public User findByUsername(String username) {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow();
    }

    public List<UserDto> getAllUsers() {
        return this.userRepository
                .findAll()
                .stream()
                .map(UserMapper::mapFromUserEntityToUserDto)
                .toList();
    }

    public UserDto getUserById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow();
        return UserMapper.mapFromUserEntityToUserDto(user);
    }

    public UserDto updateUser(Long id, UserUpdateDto userUpdateDto) {
        User user = this.userRepository.findById(id).orElseThrow();

        user
                .setUsername(userUpdateDto.getUsername())
                .setFirstName(userUpdateDto.getFirstName())
                .setLastName(userUpdateDto.getLastName())
                .setDisplayName(userUpdateDto.getDisplayName())
                .setDateOfBirth(userUpdateDto.getDateOfBirth());

        this.userRepository.save(user);

        return UserMapper.mapFromUserEntityToUserDto(user);
    }

    public UserDto deleteUserById(Long id) {
        User userToDelete = this.userRepository.findById(id).orElseThrow();

        this.userRepository.delete(userToDelete);

        return UserMapper.mapFromUserEntityToUserDto(userToDelete);
    }
}
