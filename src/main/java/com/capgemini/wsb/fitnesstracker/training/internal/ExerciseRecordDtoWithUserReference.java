package com.capgemini.wsb.fitnesstracker.training.internal;

import io.micrometer.common.lang.Nullable;

import java.util.Date;

public record ExerciseRecordDtoWithUserReference(
        @Nullable Long recordId,
        Long userReferenceId,
        Date startTime,
        Date endTime,
        double distanceCovered,
        double averagePace,
        ExerciseCategory exerciseCategory
) {}
