package com.example.ResumeDbAppJPASpring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Country {
    private int nationalityId;
    private String birthPlace;
    private int nationality;
    @Id
    private Long id;

    public Country() {
    }


    public Country(int nationalityId, Object o, String nationalityStr) {
    }

    public Country(int nationalityId, String birthPlace, int nationality) {
        this.nationalityId = nationalityId;
        this.birthPlace = birthPlace;
        this.nationality = nationality;
    }

    public Country(Object o, User user, String nationality, String birthPlace) {
    }

    public int getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(int nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public int getNationality() {
        return nationality;
    }

    public void setNationality(int nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Country{" +
                "nationalityId=" + nationalityId +
                ", birthPlace=" + birthPlace +
                ", nationality=" + nationality +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
