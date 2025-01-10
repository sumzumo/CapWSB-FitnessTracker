package com.capgemini.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

record UserDto(@Nullable Long userId, String firstName, String lastName,
               @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthDate,
               String userEmail) {  }
