package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import io.micrometer.common.lang.Nullable;

import java.util.Date;

public record ExerciseRecordDto(
        @Nullable Long recordId,
        UserDto userDetails,
        Date startTime,
        Date endTime,
        double distanceCovered,
        double averagePace,
        ExerciseCategory exerciseCategory
) {}
