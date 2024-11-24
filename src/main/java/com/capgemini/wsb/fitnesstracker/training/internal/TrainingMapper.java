package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserMapper;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Converts Training entities to Training DTOs and vice versa.
 */
@Component
@RequiredArgsConstructor
public class TrainingMapper {
    private final UserMapper userMapper;
    private final UserProvider userProvider;

    /**
     * Converts a Training entity to a Training DTO.
     *
     * @param training the Training entity
     * @return the Training DTO
     */
    TrainingDto toDto(Training training) {
        return new TrainingDto(training.getId(),
                userMapper.toDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    /**
     * Converts a Training DTO to a Training entity.
     *
     * @param trainingDto the Training DTO
     * @return the Training entity
     * @throws IllegalArgumentException if the user with the provided ID is not found
     */
    Training toEntity(TrainingDtoWithUserId trainingDto) {
        User user = userProvider.getUser(trainingDto.userId())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + trainingDto.userId() + " not found"));
        return new Training(
                user,
                trainingDto.startTime(),
                trainingDto.endTime(),
                trainingDto.activityType(),
                trainingDto.distance(),
                trainingDto.averageSpeed()
        );
    }
}
