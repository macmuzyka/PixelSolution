package com.pixel.model.non_entity_projection;

/**
 * Created by Maciej Muzyka
 * on 28.06.2021
 */

public class PractitionerVisitsDTO {
    
    private String specialization;
    private int visits;

    public PractitionerVisitsDTO() {
    }

    public PractitionerVisitsDTO(final String specialization, final int visits) {
        this.specialization = specialization;
        this.visits = visits;
    }

    String getSpecialization() {
        return specialization;
    }

    void setSpecialization(final String specialization) {
        this.specialization = specialization;
    }

    int getVisits() {
        return visits;
    }

    void setVisits(final int visits) {
        this.visits = visits;
    }
}
