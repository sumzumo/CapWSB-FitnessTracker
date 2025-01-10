package com.capgemini.wsb.fitnesstracker.loader;

import com.capgemini.wsb.fitnesstracker.training.api.ExerciseRecord;
import com.capgemini.wsb.fitnesstracker.training.internal.ExerciseCategory;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;
import static java.util.Objects.isNull;

/**
 * Sample init data loader. If the application is run with `loadInitialData` profile,
 * then on application startup it will fill the database with dummy data,
 * for manual testing purposes. Loader is triggered by {@link ContextRefreshedEvent} event.
 */
@Component
@Profile("loadInitialData")
@Slf4j
@ToString
class InitialDataLoader {

    @Autowired
    private JpaRepository<User, Long> userRepository;

    // Zmieniamy z Training na ExerciseRecord
    @Autowired
    private JpaRepository<ExerciseRecord, Long> exerciseRecordRepository;

    @EventListener
    @Transactional
    @SuppressWarnings({"squid:S1854", "squid:S1481", "squid:S1192", "unused"})
    public void loadInitialData(ContextRefreshedEvent event) {
        verifyDependenciesAutowired();

        log.info("Loading initial data to the database");

        List<User> sampleUserList = generateSampleUsers();
        List<ExerciseRecord> sampleExerciseRecordList = generateExerciseRecordData(sampleUserList);

        log.info("Finished loading initial data");
    }

    private User generateUser(String name, String lastName, int age) {
        User user = new User(
                name,
                lastName,
                now().minusYears(age),
                "%s.%s@domain.com".formatted(name, lastName)
        );
        return userRepository.save(user);
    }

    private List<User> generateSampleUsers() {
        List<User> users = new ArrayList<>();

        users.add(generateUser("Emma", "Johnson", 28));
        users.add(generateUser("Ethan", "Taylor", 51));
        users.add(generateUser("Olivia", "Davis", 76));
        users.add(generateUser("Daniel", "Thomas", 34));
        users.add(generateUser("Sophia", "Baker", 49));
        users.add(generateUser("Liam", "Jones", 23));
        users.add(generateUser("Ava", "Williams", 21));
        users.add(generateUser("Noah", "Miller", 39));
        users.add(generateUser("Grace", "Anderson", 33));
        users.add(generateUser("Oliver", "Swift", 29));

        return users;
    }

    private List<ExerciseRecord> generateExerciseRecordData(List<User> users) {
        List<ExerciseRecord> exerciseRecordData = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            // Przykładowe rekordy – wcześniej nazywane `trainingX`,
            // teraz `exerciseRecordX`.
            ExerciseRecord exerciseRecord1 = new ExerciseRecord(
                    users.get(0),
                    sdf.parse("2024-01-19 08:00:00"),
                    sdf.parse("2024-01-19 09:30:00"),
                    ExerciseCategory.RUNNING,
                    10.5,    // totalDistance
                    8.2      // meanSpeed
            );

            ExerciseRecord exerciseRecord2 = new ExerciseRecord(
                    users.get(1),
                    sdf.parse("2024-01-18 15:30:00"),
                    sdf.parse("2024-01-18 17:00:00"),
                    ExerciseCategory.CYCLING,
                    25.0,
                    18.5
            );

            ExerciseRecord exerciseRecord3 = new ExerciseRecord(
                    users.get(2),
                    sdf.parse("2024-01-17 07:45:00"),
                    sdf.parse("2024-01-17 09:00:00"),
                    ExerciseCategory.WALKING,
                    5.2,
                    5.8
            );

            // ... analogicznie do exerciseRecord4, 5, 6, ...
            // Poniżej kolejne przykłady

            ExerciseRecord exerciseRecord4 = new ExerciseRecord(
                    users.get(3),
                    sdf.parse("2024-01-16 18:00:00"),
                    sdf.parse("2024-01-16 19:30:00"),
                    ExerciseCategory.RUNNING,
                    12.3,
                    9.0
            );

            ExerciseRecord exerciseRecord5 = new ExerciseRecord(
                    users.get(4),
                    sdf.parse("2024-01-15 12:30:00"),
                    sdf.parse("2024-01-15 13:45:00"),
                    ExerciseCategory.CYCLING,
                    18.7,
                    15.3
            );

            ExerciseRecord exerciseRecord6 = new ExerciseRecord(
                    users.get(5),
                    sdf.parse("2024-01-14 09:00:00"),
                    sdf.parse("2024-01-14 10:15:00"),
                    ExerciseCategory.WALKING,
                    3.5,
                    4.0
            );

            ExerciseRecord exerciseRecord7 = new ExerciseRecord(
                    users.get(6),
                    sdf.parse("2024-01-13 16:45:00"),
                    sdf.parse("2024-01-13 18:30:00"),
                    ExerciseCategory.RUNNING,
                    15.0,
                    10.8
            );

            ExerciseRecord exerciseRecord8 = new ExerciseRecord(
                    users.get(7),
                    sdf.parse("2024-01-12 11:30:00"),
                    sdf.parse("2024-01-12 12:45:00"),
                    ExerciseCategory.CYCLING,
                    22.5,
                    17.2
            );

            ExerciseRecord exerciseRecord9 = new ExerciseRecord(
                    users.get(8),
                    sdf.parse("2024-01-11 07:15:00"),
                    sdf.parse("2024-01-11 08:30:00"),
                    ExerciseCategory.WALKING,
                    4.2,
                    4.5
            );

            ExerciseRecord exerciseRecord10 = new ExerciseRecord(
                    users.get(9),
                    sdf.parse("2024-01-10 14:00:00"),
                    sdf.parse("2024-01-10 15:15:00"),
                    ExerciseCategory.RUNNING,
                    11.8,
                    8.5
            );

            exerciseRecordData.add(exerciseRecord1);
            exerciseRecordData.add(exerciseRecord2);
            exerciseRecordData.add(exerciseRecord3);
            exerciseRecordData.add(exerciseRecord4);
            exerciseRecordData.add(exerciseRecord5);
            exerciseRecordData.add(exerciseRecord6);
            exerciseRecordData.add(exerciseRecord7);
            exerciseRecordData.add(exerciseRecord8);
            exerciseRecordData.add(exerciseRecord9);
            exerciseRecordData.add(exerciseRecord10);

            // Zapisujemy każdy rekord w bazie
            exerciseRecordData.forEach(exRecord -> exerciseRecordRepository.save(exRecord));

        } catch (Exception e) {
            // Obsługa błędu parsowania daty
            e.printStackTrace();
        }

        return exerciseRecordData;
    }

    private void verifyDependenciesAutowired() {
        if (isNull(userRepository)) {
            throw new IllegalStateException("Initial data loader was not autowired correctly " + this);
        }
        if (isNull(exerciseRecordRepository)) {
            throw new IllegalStateException("ExerciseRecord repository not injected properly " + this);
        }
    }
}
