package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.ExerciseRecord;
import com.capgemini.wsb.fitnesstracker.training.internal.ExerciseRecordRepository;
import com.capgemini.wsb.fitnesstracker.training.internal.ExerciseCategory;
import com.capgemini.wsb.fitnesstracker.training.api.ExerciseRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExerciseRecordServiceImpl implements ExerciseRecordService {

    private final ExerciseRecordRepository exerciseRecordRepository;

    @Override
    public ExerciseRecord createExerciseRecord(ExerciseRecord exerciseRecord) {
        return exerciseRecordRepository.save(exerciseRecord);
    }

    @Override
    public ExerciseRecord updateExerciseRecord(Long recordId, ExerciseRecord updatedRecord) {
        ExerciseRecord existingRecord = exerciseRecordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Record not found: " + recordId));

        existingRecord.setStartTime(updatedRecord.getStartTime());
        existingRecord.setEndTime(updatedRecord.getEndTime());
        existingRecord.setExerciseCategory(updatedRecord.getExerciseCategory());
        existingRecord.setTotalDistance(updatedRecord.getTotalDistance());
        existingRecord.setMeanSpeed(updatedRecord.getMeanSpeed());

        return exerciseRecordRepository.save(existingRecord);
    }

    @Override
    public ExerciseRecord partiallyUpdateExerciseRecord(Long recordId, Map<String, Object> updates) {
        ExerciseRecord record = exerciseRecordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Record not found: " + recordId));

        updates.forEach((key, value) -> {
            switch (key) {
                case "startTime" -> record.setStartTime((Date) value);
                case "endTime" -> record.setEndTime((Date) value);
                case "exerciseCategory" -> record.setExerciseCategory(ExerciseCategory.valueOf((String) value));
                case "totalDistance" -> record.setTotalDistance((Double) value);
                case "meanSpeed" -> record.setMeanSpeed((Double) value);
                default -> throw new IllegalArgumentException("Unknown field: " + key);
            }
        });
        return exerciseRecordRepository.save(record);
    }

    @Override
    public void deleteExerciseRecord(Long recordId) {
        exerciseRecordRepository.deleteById(recordId);
    }

    @Override
    public ExerciseRecord findExerciseRecord(Long recordId) {
        return exerciseRecordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Record not found: " + recordId));
    }

    @Override
    public List<ExerciseRecord> getAllExerciseRecords() {
        return exerciseRecordRepository.findAll();
    }

    @Override
    public List<ExerciseRecord> getExerciseRecordsByUserReference(Long userId) {
        return exerciseRecordRepository.findByUserId(userId);
    }

    @Override
    public List<ExerciseRecord> getCompletedExerciseRecords(Date endTime) {
        return exerciseRecordRepository.findByEndTimeAfter(endTime);
    }

    @Override
    public List<ExerciseRecord> getExerciseRecordsByCategory(ExerciseCategory exerciseCategory) {
        return exerciseRecordRepository.findByExerciseCategory(exerciseCategory);
    }
}
