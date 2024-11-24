package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Repository interface for accessing and managing {@link Training} entities.
 * Extends the {@link JpaRepository} to provide CRUD operations and additional query methods.
 */
@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Finds a list of trainings associated with a specific user ID.
     *
     * @param userId the ID of the user to search for trainings
     * @return a list of trainings associated with the given user ID
     */
    default List<Training> findByUserId(Long userId) {
        return findAll().stream()
                .filter(training -> training.getUser().getId().equals(userId))
                .toList();
    }

    /**
     * Finds a list of trainings associated with a specific user.
     *
     * @param user the user to search for trainings
     * @return a list of trainings associated with the given user
     */
    default List<Training> findByUserObject(User user) {
        return findAll().stream()
                .filter(training -> Objects.equals(training.getUser(), user))
                .collect(Collectors.toList());
    }

    /**
     * Finds a list of trainings that end after a specified date.
     *
     * @param afterTime the date to compare against the end time of the trainings
     * @return a list of trainings that end after the specified date
     */
    default List<Training> findByEndTimeAfter(Date afterTime) {
        return findAll().stream().filter(
                training -> Objects.compare(training.getEndTime(), afterTime, Comparator.naturalOrder()) > 0
        ).collect(Collectors.toList());
    }

    /**
     * Finds a list of trainings of a specific activity type.
     *
     * @param activityType the activity type to search for
     * @return a list of trainings with the specified activity type
     */
    default List<Training> findByActivityType(ActivityType activityType){
        return findAll().stream().filter(
                training -> Objects.equals(training.getActivityType(), activityType)
        ).collect(Collectors.toList());
    }
}
