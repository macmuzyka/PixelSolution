package com.pixel.model.non_entity_projection;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

public class ResultDTO {

    private String firstName;
    private String lastName;
    private int visits;
    private String specialization;

    ResultDTO(final String firstName, final String lastName, final int visits, final String specializaition) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.visits = visits;
        this.specialization = specializaition;
    }

    ResultDTO() {
    }

    String getSpecialization() {
        return specialization;
    }

    void setSpecialization(final String specialization) {
        this.specialization = specialization;
    }

    String getFirstName() {
        return firstName;
    }

    void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    String getLastName() {
        return lastName;
    }

    void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    int getVisits() {
        return visits;
    }

    void setVisits(final int visits) {
        this.visits = visits;
    }
}





