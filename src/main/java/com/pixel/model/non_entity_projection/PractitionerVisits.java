package com.pixel.model.non_entity_projection;

/**
 * Created by Maciej Muzyka
 * on 28.06.2021
 */

public class PractitionerVisits {

    private String specialization;
    private long visits;

    public PractitionerVisits() {
    }

    public PractitionerVisits(final String specialization, final long visits) {
        this.specialization = specialization;
        this.visits = visits;
    }

    String getSpecialization() {
        return specialization;
    }

    void setSpecialization(final String specialization) {
        this.specialization = specialization;
    }

    long getVisits() {
        return visits;
    }

    void setVisits(final int visits) {
        this.visits = visits;
    }
}
