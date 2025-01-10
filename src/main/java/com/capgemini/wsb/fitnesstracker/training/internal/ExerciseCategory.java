package com.capgemini.wsb.fitnesstracker.training.internal;


public enum ExerciseCategory {

    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SWIMMING("Swimming"),
    TENNIS("Tennis");

    private final String label;

    ExerciseCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
