// INTERFEJS: UserService
package com.capgemini.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long userId);
    Optional<User> getUser(Long userId);
    Optional<User> findUserByExactEmail(String email);
    List<User> findUsersByEmailContainingIgnoreCase(String email);
    List<User> findAllUsers();
    void addUser(User user);

    List<User> getUsersOlderThan(LocalDate date);
}
