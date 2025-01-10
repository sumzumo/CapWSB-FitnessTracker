package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
class UserEmailSimpleMapper {


    UserEmailSimpleDto convertToEmailSimpleDto(User user) {
        return new UserEmailSimpleDto(user.getUserId(), user.getUserEmail());
    }


    User convertToSimpleEmailEntity(UserEmailSimpleDto userEmailSimpleDto) {
        return new User(null, null, null, userEmailSimpleDto.userEmail());
    }
}
