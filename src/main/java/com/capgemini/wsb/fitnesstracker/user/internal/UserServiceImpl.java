package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getUserId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(final User user) {
        log.info("Updating User {}", user);
        if (user.getUserId() == null) {
            throw new IllegalArgumentException("User has no DB ID, create is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(final Long userId) {
        log.info("Deleting User with ID {}", userId);
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> findUserByExactEmail(final String email) {
        return userRepository.findUserByExactEmail(email);
    }

    @Override
    public List<User> findUsersByEmailContainingIgnoreCase(final String email) {
        return userRepository.findUsersByEmailContainingIgnoreCase(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        log.info("Adding User {}", user);
        userRepository.save(user);
    }

    @Override
    public List<User> getUsersOlderThan(LocalDate date) {
        return userRepository.findUsersByBirthDateBefore(date);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.empty();
    }

    // Metody z UserProvider
    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getUsersByEmailIgnoreCase(String email) {
        return userRepository.findUsersByEmailContainingIgnoreCase(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
