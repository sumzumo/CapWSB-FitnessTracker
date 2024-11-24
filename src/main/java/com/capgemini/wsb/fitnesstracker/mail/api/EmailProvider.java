package com.capgemini.wsb.fitnesstracker.mail.api;

import com.capgemini.wsb.fitnesstracker.training.api.Training;

import java.util.List;

/**
 * Service interface for sending emails.
 */
public interface EmailProvider {
    EmailDto sendMail(String to, String subject, List<Training> trainingList);
}