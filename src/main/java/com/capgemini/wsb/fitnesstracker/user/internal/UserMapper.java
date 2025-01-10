package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component("internalUserMapper")
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getUserEmail());
    }

    public User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthDate(),
                userDto.userEmail());
    }

    public User updateEntity(UserDto userDto, User user) {
        if (userDto.firstName() != null) {
            user.setFirstName(userDto.firstName());
        }

        if (userDto.lastName() != null) {
            user.setLastName(userDto.lastName());
        }

        if (userDto.birthDate() != null) {
            user.setBirthDate(userDto.birthDate());
        }

        if (userDto.userEmail() != null) {
            user.setUserEmail(userDto.userEmail());
        }

        return user;
    }
}
