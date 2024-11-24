package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of the TrainingService interface.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingService, TrainingProvider {

    /**
     * Repository for training entities.
     */
    private final TrainingRepository trainingRepository;

    /**
     * Returns the training with the given ID.
     *
     * @param trainingId ID of the training to be returned
     * @return training with the given ID
     */
    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    /**
     * Returns all trainings.
     *
     * @return all trainings
     */
    @Override
    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    /**
     * Returns all trainings for a given user.
     *
     * @param userId ID of the user
     * @return all trainings for the given user
     */
    @Override
    public List<Training> findTrainingByUser(final Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    /**
     * Returns all trainings for a given user.
     *
     * @param user the user for which trainings should be retrieved
     * @return a list of trainings for the specified user
     */
    @Override
    public List<Training> findByUserObject(User user) {
        return trainingRepository.findByUserObject(user);
    }

    /**
     * Returns all trainings that have finished after a given time.
     *
     * @param afterTime time after which the training has finished
     * @return all trainings that have finished after a given time
     */
    @Override
    public List<Training> findFinishedTrainings(final Date afterTime) {
        return trainingRepository.findByEndTimeAfter(afterTime);
    }

    /**
     * Returns all trainings of a specific activity type.
     *
     * @param activityType the type of activity for which trainings should be retrieved
     * @return a list of trainings of the specified activity type
     */
    @Override
    public List<Training> findTrainingsByActivityType(ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }

    /**
     * Creates a new training.
     *
     * @param training the training to be created
     * @return the created training
     */
    @Override
    public Training createTraining(Training training)
    {
        log.info("Creating training {}", training);
        if (training.getId() != null) {
            throw new IllegalArgumentException("Training id is already set");
        }
        return trainingRepository.save(training);
    }

    /**
     * Updates the training with the given ID.
     *
     * @param id ID of the training to be updated
     *           updatedTraining the updated training
     */
    @Override
    public Training updateTraining(Long id, Training updatedTraining) {
        Training existingTraining = trainingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Training not found for ID: " + id));

        if (updatedTraining.getStartTime() != null) {
            existingTraining.setStartTime(updatedTraining.getStartTime());
        }
        if (updatedTraining.getEndTime() != null) {
            existingTraining.setEndTime(updatedTraining.getEndTime());
        }
        if (updatedTraining.getActivityType() != null) {
            existingTraining.setActivityType(updatedTraining.getActivityType());
        }
        if (updatedTraining.getDistance() != 0) {
            existingTraining.setDistance(updatedTraining.getDistance());
        }
        if (updatedTraining.getAverageSpeed() != 0) {
            existingTraining.setAverageSpeed(updatedTraining.getAverageSpeed());
        }

        return trainingRepository.save(existingTraining);
    }

    @Override
    public Training partiallyUpdateTraining(Long id, Map<String, Object> updates) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingNotFoundException(id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "userId":
                    training.setUser((User) value);
                    break;
                case "startTime":
                    training.setStartTime((Date) value);
                    break;
                case "endTime":
                    training.setEndTime((Date) value);
                    break;
                case "activityType":
                    training.setActivityType(ActivityType.valueOf((String) value));
                    break;
                case "distance":
                    training.setDistance((Double) value);
                    break;
                case "averageSpeed":
                    training.setAverageSpeed((Double) value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown field: " + key);
            }
        });
        return trainingRepository.save(training);
    }

    @Override
    public void deleteTraining(Long id) {
        trainingRepository.deleteById(id);
    }
}
