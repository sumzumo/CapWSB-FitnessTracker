package com.capgemini.wsb.fitnesstracker.training.internal;

/**
 * Enum representing the type of activity.
 */
public enum ActivityType {
    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SWIMMING("Swimming"),
    TENNIS("Tenis");

    /**
     * The display name of the activity type.
     */
    private final String displayName;

    /**
     * Constructs an activity type with the specified display name.
     *
     * @param displayName the display name of the activity type
     */
    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name of the activity type.
     *
     * @return the display name of the activity type
     */
    public String getDisplayName() {
        return displayName;
    }
}
