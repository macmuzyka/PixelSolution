package com.pixel.model;

import javax.persistence.*;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */


@Entity
@Table(name = "visits")
public class Visit extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int practitioner_id;
    private int patient_id;

    public Visit() {
    }

    public Visit(final int id, final int practitioner_id, final int patient_id) {
        this.id = id;
        this.practitioner_id = practitioner_id;
        this.patient_id = patient_id;
    }

    public Visit(final int practitioner_id, final int patient_id) {
        this.practitioner_id = practitioner_id;
        this.patient_id = patient_id;
    }

    public int getId() {
        return id;
    }

    void setId(final int id) {
        this.id = id;
    }

    public int getPractitioner_id() {
        return practitioner_id;
    }

    void setPractitioner_id(final int practitioner_id) {
        this.practitioner_id = practitioner_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    void setPatient_id(final int patient_id) {
        this.patient_id = patient_id;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", practitioner_id=" + practitioner_id +
                ", patient_id=" + patient_id +
                '}';
    }
}
