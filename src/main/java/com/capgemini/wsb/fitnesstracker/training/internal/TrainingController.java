package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Controller class for handling training-related HTTP requests.
 */
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {
    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsByUser(@PathVariable Long userId) {
        return trainingService.findTrainingByUser(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getFinishedTrainings(@PathVariable ("afterTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime) {
        return trainingService.findFinishedTrainings(afterTime)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a list of trainings of a specific activity type.
     *
     * @param activityType the activity type to search for
     * @return a list of DTO representations of trainings
     */
    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivityType(@RequestParam ("activityType") ActivityType activityType) {
        return trainingService.findTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Creates a new training record.
     *
     * @param trainingDto the DTO representation of the training to create
     * @return the DTO representation of the created training
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody TrainingDtoWithUserId trainingDto) {
        Training training = trainingMapper.toEntity(trainingDto);
        Training savedTraining = trainingService.createTraining(training);
        return trainingMapper.toDto(savedTraining);
    }

    /**
     * Updates an existing training record.
     *
     * @param id          the ID of the training to update
     * @param trainingDto the DTO representation of the updated training
     * @return the DTO representation of the updated training
     */
    @PutMapping("/{id}")
    public TrainingDto updateTraining(@PathVariable Long id, @RequestBody TrainingDtoWithUserId trainingDto) {
        Training updatedTraining = trainingService.updateTraining(id, trainingMapper.toEntity(trainingDto));
        return trainingMapper.toDto(updatedTraining);
    }

    /**
     * Partially updates an existing training record.
     *
     * @param id      the ID of the training to update
     * @param updates a map containing the fields to update and their new values
     * @return the DTO representation of the updated training
     */
    @PatchMapping("/{id}")
    public TrainingDto partiallyUpdateTraining(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        Training updatedTraining = trainingService.partiallyUpdateTraining(id, updates);
        return trainingMapper.toDto(updatedTraining);
    }

    /**
     * Deletes an existing training record.
     *
     * @param id the ID of the training to delete
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteTraining(@PathVariable Long id) {
        trainingService.deleteTraining(id);
        return ResponseEntity.noContent().build();
    }
}
