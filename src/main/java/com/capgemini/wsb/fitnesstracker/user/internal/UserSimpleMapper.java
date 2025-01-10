package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
class UserSimpleMapper {

    UserSimpleDto toUserSimpleDto(User user) {
        return new UserSimpleDto(user.getUserId(), user.getFirstName(), user.getLastName());
    }

    User toUserSimpleEntity(UserSimpleDto userDto) {
        return new User(userDto.firstName(), userDto.lastName(), null, null);
    }

    public UserSimpleDto convertToSimpleDto(User user) {
        return toUserSimpleDto(user);
    }
}
