package com.pixel.model;

import javax.persistence.*;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@Entity
@Table(name = "patient_to_practitioner")
public class PatientToPractitioner extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int patient_id;
    private int practitioner_id;

    public PatientToPractitioner() {
    }

    public PatientToPractitioner(final int patient_id, final int practitioner_id) {

        this.patient_id = patient_id;
        this.practitioner_id = practitioner_id;
    }

    int getPatient_id() {
        return patient_id;
    }

    void setPatient_id(final int patient_id) {
        this.patient_id = patient_id;
    }

    int getPractitioner_id() {
        return practitioner_id;
    }

    void setPractitioner_id(final int practitioner_id) {
        this.practitioner_id = practitioner_id;
    }

    @Override
    public String toString() {
        return "PatientToPractitioner{" +
                "patient_id=" + patient_id +
                ", practitioner_id=" + practitioner_id +
                '}';
    }
}
