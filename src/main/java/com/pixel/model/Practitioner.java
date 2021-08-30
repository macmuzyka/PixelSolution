package com.pixel.model;

import javax.persistence.*;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@Entity
@Table(name = "practitioners")
public class Practitioner extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String specialization;

    public Practitioner() {
    }

    public Practitioner(final int id, final String specialization) {
        this.id = id;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    void setId(final int id) {
        this.id = id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(final String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Practitioner{" +
                "id=" + id +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
