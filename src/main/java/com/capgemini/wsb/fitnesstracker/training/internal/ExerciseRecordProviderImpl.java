package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.ExerciseRecord;
import com.capgemini.wsb.fitnesstracker.training.api.ExerciseRecordProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseRecordProviderImpl implements ExerciseRecordProvider {

    private final ExerciseRecordRepository exerciseRecordRepository;

    @Override
    public List<ExerciseRecord> getAllExerciseRecords() {
        List<ExerciseRecord> records = exerciseRecordRepository.findAll();
        return records != null ? records : List.of();
    }

    @Override
    public List<ExerciseRecord> findExerciseRecordsByUser(Long userId) {
        List<ExerciseRecord> records = exerciseRecordRepository.findByUserId(userId);
        return records != null ? records : List.of();
    }

    @Override
    public List<ExerciseRecord> getExerciseRecordsByUserProfile(com.capgemini.wsb.fitnesstracker.user.api.User userProfile) {
        List<ExerciseRecord> records = exerciseRecordRepository.findByUserId(userProfile.getUserId());
        return records != null ? records : List.of();
    }

    @Override
    public List<ExerciseRecord> getCompletedExerciseRecords(Date endTime) {
        List<ExerciseRecord> records = exerciseRecordRepository.findByEndTimeAfter(endTime);
        return records != null ? records : List.of();
    }

    @Override
    public List<ExerciseRecord> getExerciseRecordsByCategory(ExerciseCategory exerciseCategory) {
        List<ExerciseRecord> records = exerciseRecordRepository.findByExerciseCategory(exerciseCategory);
        return records != null ? records : List.of();
    }

    @Override
    public ExerciseRecord getExerciseRecord(Long recordId) {
        return exerciseRecordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Record not found: " + recordId));
    }
}
