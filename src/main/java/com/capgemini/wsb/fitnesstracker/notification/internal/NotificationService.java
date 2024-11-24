package com.capgemini.wsb.fitnesstracker.notification.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailProvider;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
@Slf4j
@EnableScheduling
public class NotificationService {
    private final EmailSender emailSender;
    private final EmailProvider emailProvider;
    private final TrainingProvider trainingProvider;
    private final UserProvider userProvider;
    private final String reportString="Monthy report";


    /**
     * Generates report and sends it to all users.
     */
    @Scheduled(cron = "0 0 8 9 * *")
    public void generateReportAndSendMail() {
        System.out.println("Cron scheduling report generation");
        List<User> allUsers = userProvider.findAllUsers();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneWeekAgo = now.minus(1, ChronoUnit.MONTHS);

        for (User user : allUsers) {
            List<Training> recentTrainings = trainingProvider.findTrainingByUser(user.getId()).stream()
                    .filter(training -> toLocalDateTime(training.getStartTime()).isAfter(oneWeekAgo))
                    .collect(Collectors.toList());
            if (!recentTrainings.isEmpty()) {
                final EmailDto emailDto = emailProvider.sendMail(user.getEmail(),
                        reportString,
                        recentTrainings);
                emailSender.send(emailDto);
                System.out.println("sending email");
            }
        }
    }

    /**
     * Converts Date to LocalDateTime.
     *
     * @param date Date to be converted
     * @return LocalDateTime
     */
    private LocalDateTime toLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}