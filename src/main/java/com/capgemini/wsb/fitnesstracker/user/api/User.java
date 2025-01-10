package com.capgemini.wsb.fitnesstracker.user.api;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, unique = true)
    private String userEmail;

    public User(String firstName, String lastName, LocalDate birthDate, String userEmail) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        if (birthDate == null) {
            throw new IllegalArgumentException("Birthdate cannot be null");
        }
        if (userEmail == null || userEmail.isEmpty()) {
            throw new IllegalArgumentException("User email cannot be null or empty");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.userEmail = userEmail;
    }

    public boolean isAboveMinimumAge(int minimumAge) {
        Period age = Period.between(birthDate, LocalDate.now());
        return age.getYears() >= minimumAge;
    }
}
