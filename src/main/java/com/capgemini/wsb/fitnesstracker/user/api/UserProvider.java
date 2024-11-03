package com.capgemini.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserProvider {

    Optional<User> getUser(Long userId);

    List<User> getUsersOlderThan(LocalDate date);

    Optional<User> getUserByEmail(String email);

    List<User> getUserByEmailIgnoreCase(String email);

    List<User> findAllUsers();
}
