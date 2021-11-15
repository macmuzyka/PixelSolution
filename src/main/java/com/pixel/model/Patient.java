package com.pixel.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@Entity
@Table(name = "patients")
public class Patient extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;

    private String city;

    private LocalDate createdAt;

    public Patient() {
    }

    public Patient(final int id, final String firstName, final String lastName, final String city, final LocalDate createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.createdAt = createdAt;
    }

    public void updateFrom(final Patient sourcePatient) {
        firstName = sourcePatient.getFirstName();
        lastName = sourcePatient.getLastName();
        city = sourcePatient.getCity();
    }

    public int getId() {
        return id;
    }

    void setId(final int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    void setCreatedAt(final LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
