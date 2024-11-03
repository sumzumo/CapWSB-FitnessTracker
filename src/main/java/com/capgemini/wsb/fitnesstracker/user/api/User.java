package com.capgemini.wsb.fitnesstracker.user.api;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ID użytkownika

    @Column(name = "first_name", nullable = false)
    private String firstName;  // Imię

    @Column(name = "last_name", nullable = false)
    private String lastName;  // Nazwisko

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;  // Data urodzenia

    @Column(nullable = false, unique = true)
    private String email;  // E-mail użytkownika

    // Konstruktory
    public User(String firstName, String lastName, LocalDate birthdate, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }
}
