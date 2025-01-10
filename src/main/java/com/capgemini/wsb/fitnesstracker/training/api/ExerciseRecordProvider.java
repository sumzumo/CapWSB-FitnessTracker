package com.capgemini.wsb.fitnesstracker.training.api;
import com.capgemini.wsb.fitnesstracker.training.internal.ExerciseCategory;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;

public interface ExerciseRecordProvider {

    List<ExerciseRecord> getAllExerciseRecords();

    List<ExerciseRecord> findExerciseRecordsByUser(Long userId);

    List<ExerciseRecord> getExerciseRecordsByUserProfile(User userProfile);

    List<ExerciseRecord> getCompletedExerciseRecords(Date endTime);

    List<ExerciseRecord> getExerciseRecordsByCategory(ExerciseCategory exerciseCategory);

    ExerciseRecord getExerciseRecord(Long recordId);
}
