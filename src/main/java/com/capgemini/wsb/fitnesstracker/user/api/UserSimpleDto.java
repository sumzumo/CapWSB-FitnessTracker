package com.capgemini.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

public record UserSimpleDto(@Nullable Long userId, String firstName, String lastName) {}
