package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.internal.ExerciseCategory;
import com.capgemini.wsb.fitnesstracker.training.api.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, Long> {


    @Query("SELECT e FROM ExerciseRecord e WHERE e.user.id = :userId")
    List<ExerciseRecord> findByUserId(@Param("userId") Long userId);


    @Query("SELECT e FROM ExerciseRecord e WHERE e.endTime > :afterTime")
    List<ExerciseRecord> findByEndTimeAfter(@Param("afterTime") Date afterTime);

    @Query("SELECT e FROM ExerciseRecord e WHERE e.exerciseCategory = :exerciseCategory")
    List<ExerciseRecord> findByExerciseCategory(@Param("exerciseCategory") ExerciseCategory exerciseCategory);
}
