package com.capgemini.wsb.fitnesstracker.training;

import com.capgemini.wsb.fitnesstracker.IntegrationTest;
import com.capgemini.wsb.fitnesstracker.IntegrationTestBase;
import com.capgemini.wsb.fitnesstracker.training.api.ExerciseRecord;
import com.capgemini.wsb.fitnesstracker.training.internal.ExerciseCategory;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.time.LocalDate.now;
import static java.util.UUID.randomUUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@Transactional
@AutoConfigureMockMvc(addFilters = false)
class ExerciseRecordApiIntegrationTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllExerciseRecords_whenGettingAllExerciseRecords() throws Exception {

        User user1 = existingUser(generateClient());
        ExerciseRecord exerciseRecord1 = persistExerciseRecord(generateExerciseRecord(user1));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+00:00");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));

        mockMvc.perform(get("/v1/exerciseRecords").contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].user.userId").value(user1.getUserId()))
                .andExpect(jsonPath("$[0].user.firstName").value(user1.getFirstName()))
                .andExpect(jsonPath("$[0].user.lastName").value(user1.getLastName()))
                .andExpect(jsonPath("$[0].user.userEmail").value(user1.getUserEmail()))
                .andExpect(jsonPath("$[0].startTime").value(sdf.format(exerciseRecord1.getStartTime())))
                .andExpect(jsonPath("$[0].endTime").value(sdf.format(exerciseRecord1.getEndTime())))
                .andExpect(jsonPath("$[0].totalDistance").value((exerciseRecord1.getTotalDistance())))
                .andExpect(jsonPath("$[0].meanSpeed").value(exerciseRecord1.getMeanSpeed()))
                .andExpect(jsonPath("$[1]").doesNotExist());
    }

    @Test
    void shouldReturnAllExerciseRecordsForDedicatedUser_whenGettingAllExerciseRecordsForDedicatedUser() throws Exception {

        User user1 = existingUser(generateClient());
        ExerciseRecord exerciseRecord1 = persistExerciseRecord(generateExerciseRecord(user1));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+00:00");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));

        mockMvc.perform(get("/v1/exerciseRecords/{userId}", user1.getUserId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].user.userId").value(user1.getUserId()))
                .andExpect(jsonPath("$[0].user.firstName").value(user1.getFirstName()))
                .andExpect(jsonPath("$[0].user.lastName").value(user1.getLastName()))
                .andExpect(jsonPath("$[0].user.userEmail").value(user1.getUserEmail()))
                .andExpect(jsonPath("$[0].startTime").value(sdf.format(exerciseRecord1.getStartTime())))
                .andExpect(jsonPath("$[0].endTime").value(sdf.format(exerciseRecord1.getEndTime())))
                .andExpect(jsonPath("$[0].totalDistance").value((exerciseRecord1.getTotalDistance())))
                .andExpect(jsonPath("$[0].meanSpeed").value(exerciseRecord1.getMeanSpeed()))
                .andExpect(jsonPath("$[1]").doesNotExist());
    }

    @Test
    void shouldReturnAllFinishedExerciseRecordsAfterTime_whenGettingAllFinishedExerciseRecordsAfterTime() throws Exception {

        User user1 = existingUser(generateClient());
        ExerciseRecord exerciseRecord1 = persistExerciseRecord(generateExerciseRecordWithDetails(user1, "2024-05-19 19:00:00", "2024-05-19 20:30:00", ExerciseCategory.RUNNING, 14, 11.5));
        ExerciseRecord exerciseRecord2 = persistExerciseRecord(generateExerciseRecordWithDetails(user1, "2024-05-17 19:00:00", "2024-05-17 20:30:00", ExerciseCategory.RUNNING, 14, 11.5));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+00:00");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));

        mockMvc.perform(get("/v1/exerciseRecords/completed/{afterTime}", "2024-05-18").contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].user.userId").value(user1.getUserId()))
                .andExpect(jsonPath("$[0].user.firstName").value(user1.getFirstName()))
                .andExpect(jsonPath("$[0].user.lastName").value(user1.getLastName()))
                .andExpect(jsonPath("$[0].user.userEmail").value(user1.getUserEmail()))
                .andExpect(jsonPath("$[0].startTime").value(sdf.format(exerciseRecord1.getStartTime())))
                .andExpect(jsonPath("$[0].endTime").value(sdf.format(exerciseRecord1.getEndTime())))
                .andExpect(jsonPath("$[0].totalDistance").value((exerciseRecord1.getTotalDistance())))
                .andExpect(jsonPath("$[0].meanSpeed").value(exerciseRecord1.getMeanSpeed()))
                .andExpect(jsonPath("$[1]").doesNotExist());
    }

    private static User generateClient() {
        return new User(randomUUID().toString(), randomUUID().toString(), now(), randomUUID().toString());
    }

    private static ExerciseRecord generateExerciseRecord(User user) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return new ExerciseRecord(
                user,
                sdf.parse("2024-01-19 08:00:00"),
                sdf.parse("2024-01-19 09:30:00"),
                ExerciseCategory.RUNNING,
                10.5,
                8.2);
    }

    private static ExerciseRecord generateExerciseRecordWithDetails(User user, String startTime, String endTime, ExerciseCategory exerciseCategory, double distance, double meanSpeed) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return new ExerciseRecord(
                user,
                sdf.parse(startTime),
                sdf.parse(endTime),
                exerciseCategory,
                distance,
                meanSpeed);
    }
}
