package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;

@SuppressWarnings("squid:S110")
public class ExerciseRecordNotFoundException extends NotFoundException {

    private ExerciseRecordNotFoundException(String message) {
        super(message);
    }

    public ExerciseRecordNotFoundException(Long recordId) {
        this("Exercise record with ID=%s was not found".formatted(recordId));
    }
}
