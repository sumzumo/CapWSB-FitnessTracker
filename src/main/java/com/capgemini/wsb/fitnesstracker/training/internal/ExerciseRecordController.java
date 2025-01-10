package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.ExerciseRecord;
import com.capgemini.wsb.fitnesstracker.training.api.ExerciseRecordService; // <-- Używamy interfejsu
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/exerciseRecords")
@RequiredArgsConstructor
class ExerciseRecordController {

    private final ExerciseRecordService exerciseRecordService;
    private final ExerciseRecordMapper exerciseRecordMapper;

    @GetMapping
    public List<ExerciseRecordDto> getAllExerciseRecords() {
        return exerciseRecordService.getAllExerciseRecords()
                .stream()
                .map(exerciseRecordMapper::toDto)
                .toList();
    }

    @GetMapping("/{userId}")
    public List<ExerciseRecordDto> getExerciseRecordsByUser(@PathVariable Long userId) {
        return exerciseRecordService.getExerciseRecordsByUserReference(userId)
                .stream()
                .map(exerciseRecordMapper::toDto)
                .toList();
    }

    @GetMapping("/completed/{afterTime}")
    public List<ExerciseRecordDto> getCompletedExerciseRecords(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime
    ) {
        return exerciseRecordService.getCompletedExerciseRecords(afterTime)
                .stream()
                .map(exerciseRecordMapper::toDto)
                .toList();
    }

    @GetMapping("/category")
    public List<ExerciseRecordDto> getExerciseRecordsByCategory(
            @RequestParam ExerciseCategory exerciseCategory
    ) {
        return exerciseRecordService.getExerciseRecordsByCategory(exerciseCategory)
                .stream()
                .map(exerciseRecordMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseRecordDto createExerciseRecord(
            @RequestBody ExerciseRecordDtoWithUserReference exerciseRecordDto
    ) {
        ExerciseRecord exerciseRecord = exerciseRecordMapper.toEntity(exerciseRecordDto);
        ExerciseRecord savedExerciseRecord = exerciseRecordService.createExerciseRecord(exerciseRecord);
        return exerciseRecordMapper.toDto(savedExerciseRecord);
    }

    @PutMapping("/{id}")
    public ExerciseRecordDto updateExerciseRecord(
            @PathVariable Long id,
            @RequestBody ExerciseRecordDtoWithUserReference exerciseRecordDto
    ) {
        ExerciseRecord updatedExerciseRecord =
                exerciseRecordService.updateExerciseRecord(id, exerciseRecordMapper.toEntity(exerciseRecordDto));
        return exerciseRecordMapper.toDto(updatedExerciseRecord);
    }

    @PatchMapping("/{id}")
    public ExerciseRecordDto partiallyUpdateExerciseRecord(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) {
        ExerciseRecord updatedExerciseRecord =
                exerciseRecordService.partiallyUpdateExerciseRecord(id, updates);
        return exerciseRecordMapper.toDto(updatedExerciseRecord);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteExerciseRecord(@PathVariable Long id) {
        exerciseRecordService.deleteExerciseRecord(id);
        return ResponseEntity.noContent().build();
    }
}
