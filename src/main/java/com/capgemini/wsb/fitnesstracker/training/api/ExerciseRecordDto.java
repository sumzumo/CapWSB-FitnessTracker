package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ExerciseCategory;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import jakarta.annotation.Nullable;

import java.util.Date;

public record ExerciseRecordDto(
        @Nullable Long recordId,
        Date startTime,
        Date endTime,
        UserDto userProfile,
        ExerciseCategory exerciseCategory,
        double totalDistance,
        double meanSpeed
) {}
