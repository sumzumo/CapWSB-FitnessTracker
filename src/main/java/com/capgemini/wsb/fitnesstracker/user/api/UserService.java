package com.capgemini.wsb.fitnesstracker.user.api;

/**
 * Interface for modifying operations on User entities through the API.
 */
public interface UserService {

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long userId);
}
