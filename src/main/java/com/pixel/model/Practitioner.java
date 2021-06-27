package com.pixel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@Entity
@Table(name = "practitioners")
public class Practitioner {

    @Id
    private int id;
    private String specialization;

    public Practitioner() {
    }

    public Practitioner(final int id, final String specialization) {
        this.id = id;
        this.specialization = specialization;
    }

    int getId() {
        return id;
    }

    void setId(final int id) {
        this.id = id;
    }

    String getSpecialization() {
        return specialization;
    }

    void setSpecialization(final String specialization) {
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
