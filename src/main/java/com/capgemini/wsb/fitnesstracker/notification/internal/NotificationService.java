package com.capgemini.wsb.fitnesstracker.notification.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.MailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.MailProvider;
import com.capgemini.wsb.fitnesstracker.mail.api.MailSender;
import com.capgemini.wsb.fitnesstracker.training.api.ExerciseRecord;
import com.capgemini.wsb.fitnesstracker.training.api.ExerciseRecordProvider;
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

    private final MailSender mailSender;
    private final MailProvider mailProvider;
    private final ExerciseRecordProvider exerciseRecordProvider;
    private final UserProvider userProvider;

    private final String reportString = "Monthly report";

    @Scheduled(cron = "0 0 8 9 * *")
    public void generateReportAndSendMail() {
        log.info("Cron scheduling report generation");
        List<User> allUsers = userProvider.getAllUsers();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMonthAgo = now.minus(1, ChronoUnit.MONTHS);

        for (User user : allUsers) {

            List<ExerciseRecord> recentExerciseRecords = exerciseRecordProvider
                    .findExerciseRecordsByUser(user.getUserId())
                    .stream()

                    .filter(record -> toLocalDateTime(record.getStartTime()).isAfter(oneMonthAgo))
                    .collect(Collectors.toList());

            if (!recentExerciseRecords.isEmpty()) {

                final MailDto mailDto = mailProvider.sendEmail(
                        user.getUserEmail(),
                        reportString,
                        recentExerciseRecords
                );


                mailSender.send(mailDto);
                log.info("Report email sent to {}", user.getUserEmail());
            }
        }
    }

    private LocalDateTime toLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
