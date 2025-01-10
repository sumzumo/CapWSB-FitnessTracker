package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
interface UserRepository extends JpaRepository<User, Long> {


    default Optional<User> findUserByExactEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getUserEmail(), email))
                .findFirst();
    }


    default List<User> findUsersByEmailContainingIgnoreCase(String emailFragment) {
        return findAll().stream()
                .filter(user -> user.getUserEmail().toLowerCase().contains(emailFragment.toLowerCase()))
                .toList();
    }


    default List<User> findUsersByBirthDateBefore(LocalDate date) {
        return findAll().stream()
                .filter(user -> user.getBirthDate().isBefore(date))
                .toList();
    }
}
