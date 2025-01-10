package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ExerciseCategory;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ExerciseRecordService {

    ExerciseRecord createExerciseRecord(ExerciseRecord exerciseRecord);
    ExerciseRecord updateExerciseRecord(Long recordId, ExerciseRecord exerciseRecord);
    ExerciseRecord partiallyUpdateExerciseRecord(Long recordId, Map<String, Object> updates);
    void deleteExerciseRecord(Long recordId);
    ExerciseRecord findExerciseRecord(Long recordId);
    List<ExerciseRecord> getAllExerciseRecords();
    List<ExerciseRecord> getExerciseRecordsByUserReference(Long userId);
    List<ExerciseRecord> getCompletedExerciseRecords(Date endTime);
    List<ExerciseRecord> getExerciseRecordsByCategory(ExerciseCategory exerciseCategory);
}

