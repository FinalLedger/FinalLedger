package com.finalledger.models;


import javax.persistence.*;

@Entity
@Table(name = "medical_information")
public class MedicalInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String willLocation;

    @Column(nullable = false)
    private String POADocLocation;

    @Column(nullable = false)
    private String DNROrderLocation;

    @Column(nullable = false)
    private String bloodType;

    @Column(nullable = true)
    private String medicalConditions;

    @Column(nullable = false)
    private String healthInsuranceName;

    @OneToOne
    private User user;

    public MedicalInformation() {}

    public MedicalInformation(String willLocation, String POADocLocation, String DNROrderLocation, String bloodType, String medicalConditions, String healthInsuranceName, User user) {
        this.willLocation = willLocation;
        this.POADocLocation = POADocLocation;
        this.DNROrderLocation = DNROrderLocation;
        this.bloodType = bloodType;
        this.medicalConditions = medicalConditions;
        this.healthInsuranceName = healthInsuranceName;
        this.user = user;
    }

    public MedicalInformation(Long id, String willLocation, String POADocLocation, String DNROrderLocation, String bloodType, String medicalConditions, String healthInsuranceName, User user) {
        this.id = id;
        this.willLocation = willLocation;
        this.POADocLocation = POADocLocation;
        this.DNROrderLocation = DNROrderLocation;
        this.bloodType = bloodType;
        this.medicalConditions = medicalConditions;
        this.healthInsuranceName = healthInsuranceName;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWillLocation() {
        return willLocation;
    }

    public void setWillLocation(String willLocation) {
        this.willLocation = willLocation;
    }

    public String getPOADocLocation() {
        return POADocLocation;
    }

    public void setPOADocLocation(String POADocLocation) {
        this.POADocLocation = POADocLocation;
    }

    public String getDNROrderLocation() {
        return DNROrderLocation;
    }

    public void setDNROrderLocation(String DNROrderLocation) {
        this.DNROrderLocation = DNROrderLocation;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getMedicalConditions() {
        return medicalConditions;
    }

    public void setMedicalConditions(String medicalConditions) {
        this.medicalConditions = medicalConditions;
    }

    public String getHealthInsuranceName() {
        return healthInsuranceName;
    }

    public void setHealthInsuranceName(String healthInsuranceName) {
        this.healthInsuranceName = healthInsuranceName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
