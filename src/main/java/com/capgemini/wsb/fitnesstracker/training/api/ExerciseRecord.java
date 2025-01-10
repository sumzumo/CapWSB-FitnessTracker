package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ExerciseCategory;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class ExerciseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Enumerated(EnumType.STRING)
    private ExerciseCategory exerciseCategory;

    private double totalDistance;
    private double meanSpeed;

    public ExerciseRecord() {}

    public ExerciseRecord(User user,
                          Date startTime,
                          Date endTime,
                          ExerciseCategory exerciseCategory,
                          double totalDistance,
                          double meanSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.exerciseCategory = exerciseCategory;
        this.totalDistance = totalDistance;
        this.meanSpeed = meanSpeed;
    }
}
