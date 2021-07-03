package com.pixel.model.non_entity_projection;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

public class PatientVisits {

    private String firstName;
    private String lastName;
    private long visits;

    public PatientVisits(final String firstName, final String lastName, final long visits) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.visits = visits;
    }

    public PatientVisits() {
    }

    String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    long getVisits() {
        return visits;
    }

    public void setVisits(final int visits) {
        this.visits = visits;
    }
}





