package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.ExerciseRecord;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserMapper;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseRecordMapper {

    private final UserMapper userMapper;
    private final UserProvider userProvider;

    public ExerciseRecordDto toDto(ExerciseRecord exerciseRecord) {

        return new ExerciseRecordDto(
                exerciseRecord.getRecordId(),
                userMapper.toDto(exerciseRecord.getUser()),
                exerciseRecord.getStartTime(),
                exerciseRecord.getEndTime(),
                exerciseRecord.getTotalDistance(),
                exerciseRecord.getMeanSpeed(),
                exerciseRecord.getExerciseCategory()
        );
    }

    public ExerciseRecord toEntity(ExerciseRecordDtoWithUserReference exerciseRecordDto) {
        User user = userProvider.getUserById(exerciseRecordDto.userReferenceId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "User with ID " + exerciseRecordDto.userReferenceId() + " not found"));

        return new ExerciseRecord(
                user,
                exerciseRecordDto.startTime(),
                exerciseRecordDto.endTime(),
                exerciseRecordDto.exerciseCategory(),
                exerciseRecordDto.distanceCovered(),
                exerciseRecordDto.averagePace()
        );
    }
}
