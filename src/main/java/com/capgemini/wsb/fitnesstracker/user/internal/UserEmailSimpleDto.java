package com.capgemini.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;

public record UserEmailSimpleDto(@Nullable Long userId, String userEmail) {}