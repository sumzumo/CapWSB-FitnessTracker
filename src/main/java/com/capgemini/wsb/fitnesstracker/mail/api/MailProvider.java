package com.capgemini.wsb.fitnesstracker.mail.api;

import com.capgemini.wsb.fitnesstracker.training.api.ExerciseRecord;

import java.util.List;

public interface MailProvider {
    MailDto sendEmail(String recipientAddress, String subject, List<ExerciseRecord> plannedExercises);
}
