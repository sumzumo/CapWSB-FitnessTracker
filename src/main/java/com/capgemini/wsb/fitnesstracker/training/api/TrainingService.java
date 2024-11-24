package com.capgemini.wsb.fitnesstracker.training.api;

import java.util.Map;

/**
 * Interface (API) for modifying operations on {@link Training} entities.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface TrainingService {

    /**
     * Creates a new training.
     *
     * @param training the training entity to be created
     * @return the created training entity
     */
    Training createTraining(Training training);

    /**
     * Updates an existing training.
     *
     * @param id the id of the training to be updated
     * @param training the training entity with updated information
     * @return the updated training entity
     */
    Training updateTraining(Long id, Training training);

    /**
     * Partially updates an existing training.
     * This allows for updating specific fields without affecting the entire entity.
     *
     * @param id the id of the training to be updated
     * @param updates a map containing the fields to be updated and their new values
     * @return the updated training entity
     */
    Training partiallyUpdateTraining(Long id, Map<String, Object> updates);

    /**
     * Deletes a training.
     *
     * @param id the id of the training to be deleted
     */
    void deleteTraining(Long id);
}