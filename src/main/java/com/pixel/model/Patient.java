package com.pixel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@Entity
@Table(name = "patients")
public class Patient extends BaseModel {

    @Id
    private int id;

    private String firstName;
    private String lastName;

    private String city;

    private LocalDateTime createdAt;


    public Patient(final int id, final String firstName, final String lastName, final String city, final LocalDateTime createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.createdAt = createdAt.truncatedTo(ChronoUnit.SECONDS);
    }

    public Patient() {
    }

    int getId() {
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

    String getCity() {
        return city;
    }

    void setCity(final String city) {
        this.city = city;
    }

    LocalDateTime getCreatedAt() {
        return createdAt;
    }

    void setCreatedAt(final LocalDateTime createdAt) {
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
