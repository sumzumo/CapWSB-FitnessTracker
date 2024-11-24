package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailProvider;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class EmailService implements EmailProvider {

    @Override
    public EmailDto sendMail(String to, String subject, List<Training> trainingList) {
        System.out.println("Creating mail to:" + to);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastWeek = now.minusWeeks(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        StringBuilder reportString = new StringBuilder();
        reportString.append("Monthly report \n");
        reportString.append("For ").append(to).append("\n");
        reportString.append("Generated from: ").append(lastWeek.format(formatter))
                .append(" to: ").append(now.format(formatter));
        reportString.append("Amount of trainings:").append(trainingList.size()).append("\n");
        reportString.append("Traning report:");
        for (Training training : trainingList) {
            reportString.append("Training Id: ").append(training.getId()).append("\n");
            reportString.append("Start time").append(training.getStartTime()).append("\n");
            reportString.append("End time").append(training.getEndTime()).append("\n");
            reportString.append("Activity type: ").append(training.getActivityType()).append("\n");
            reportString.append("Distance: ").append(training.getDistance()).append("\n");
            reportString.append("Average speed: ").append(training.getAverageSpeed()).append("\n");
        }
        reportString.append("End of report\n");
        EmailDto mail = new EmailDto(to, subject, reportString.toString());
        System.out.println("Mail created");
        return mail;
    }
}