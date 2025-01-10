package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.MailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.MailProvider;
import com.capgemini.wsb.fitnesstracker.training.api.ExerciseRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class MailService implements MailProvider {

    @Override
    public MailDto sendEmail(String recipient, String subject, List<ExerciseRecord> exerciseList) {

        System.out.println("Creating email to:" + recipient);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastWeek = now.minusWeeks(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        StringBuilder reportString = new StringBuilder();
        reportString.append("Monthly report \n");
        reportString.append("For ").append(recipient).append("\n");
        reportString.append("Generated from: ").append(lastWeek.format(formatter))
                .append(" to: ").append(now.format(formatter));
        reportString.append("Amount of exercises:").append(exerciseList.size()).append("\n");
        reportString.append("Exercise report:");
        for (ExerciseRecord exercise : exerciseList) {
            reportString.append("Exercise Id: ").append(exercise.getExerciseCategory()).append("\n");
            reportString.append("Start time: ").append(exercise.getStartTime()).append("\n");
            reportString.append("End time: ").append(exercise.getEndTime()).append("\n");
            reportString.append("Activity type: ").append(exercise.getExerciseCategory()).append("\n");
            reportString.append("Distance: ").append(exercise.getTotalDistance()).append("\n");
            reportString.append("Average speed: ").append(exercise.getMeanSpeed()).append("\n");
        }
        reportString.append("End of report\n");

        MailDto email = new MailDto(recipient, subject, reportString.toString());
        System.out.println("Email created");
        return email;
    }
}
