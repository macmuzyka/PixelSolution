package com.pixel.model.non_entity_projection;

import java.math.BigInteger;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

public class PatientVisitsDTO {

    private String firstName;
    private String lastName;
    private BigInteger visits;

    public PatientVisitsDTO(final String firstName, final String lastName, final BigInteger visits) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.visits = visits;
    }

    public PatientVisitsDTO() {
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

    BigInteger getVisits() {
        return visits;
    }

    void setVisits(final BigInteger visits) {
        this.visits = visits;
    }
}





