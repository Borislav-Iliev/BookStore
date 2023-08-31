package com.example.bookstore.model.mappings;

import com.example.bookstore.model.dto.user.UserDto;
import com.example.bookstore.model.dto.user.UserLoginDto;
import com.example.bookstore.model.dto.user.UserRegisterDto;
import com.example.bookstore.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto mapFromUserEntityToUserDto(User userEntity) {
        return new UserDto()
                .setId(userEntity.getId())
                .setUsername(userEntity.getUsername())
                .setFirstName(userEntity.getFirstName())
                .setLastName(userEntity.getLastName())
                .setDisplayName(userEntity.getDisplayName())
                .setDateOfBirth(userEntity.getDateOfBirth())
                .setRole(userEntity.getRole());
    }

    public static User mapFromUserRegisterDtoToUserEntity(UserRegisterDto userRegisterDto) {
        return new User()
                .setUsername(userRegisterDto.getUsername())
                .setFirstName(userRegisterDto.getFirstName())
                .setLastName(userRegisterDto.getLastName())
                .setDisplayName(userRegisterDto.getDisplayName())
                .setDateOfBirth(userRegisterDto.getDateOfBirth());
    }

    public static UserLoginDto mapFromUserEntityToUserLoginDto(User userEntity) {
        return new UserLoginDto()
                .setUsername(userEntity.getUsername())
                .setPassword(userEntity.getPassword());
    }
}
