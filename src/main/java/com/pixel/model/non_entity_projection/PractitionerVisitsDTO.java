package com.pixel.model.non_entity_projection;

import java.math.BigInteger;

/**
 * Created by Maciej Muzyka
 * on 28.06.2021
 */

public class PractitionerVisitsDTO {

    private String specialization;
    private BigInteger visits;

    public PractitionerVisitsDTO() {
    }

    public PractitionerVisitsDTO(final String specialization, final BigInteger visits) {
        this.specialization = specialization;
        this.visits = visits;
    }

    String getSpecialization() {
        return specialization;
    }

    void setSpecialization(final String specialization) {
        this.specialization = specialization;
    }

    BigInteger getVisits() {
        return visits;
    }

    void setVisits(final BigInteger visits) {
        this.visits = visits;
    }
}
