package com.finalledger.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "personal_information")
public class PersonalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String legalName;

    @Column(nullable = true)
    private String maidenName;

    @Column(nullable = false)
    private String primaryAddress;

    @Column(nullable = false, unique=true)
    private String phoneNumber;

    @Column(nullable = false)
    private String birthPlace;

    @Column(nullable = false)
    private String maritalStatus;

    @Column(nullable = true)
    private String occupation;

    @Column(nullable = false)
    private String citizenship;

    @Column(nullable = false)
    private String religion;

    @Column(nullable = false)
    private String militaryStatus;

    @OneToOne
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "personalAddresses")
    private List<Address> address;

    public PersonalInformation() {
    }

    public PersonalInformation(String legalName, String maidenName, String primaryAddress, String phoneNumber, String birthPlace, String maritalStatus, String occupation, String citizenship, String religion, String militaryStatus, User user, List<Address> address) {
        this.legalName = legalName;
        this.maidenName = maidenName;
        this.primaryAddress = primaryAddress;
        this.phoneNumber = phoneNumber;
        this.birthPlace = birthPlace;
        this.maritalStatus = maritalStatus;
        this.occupation = occupation;
        this.citizenship = citizenship;
        this.religion = religion;
        this.militaryStatus = militaryStatus;
        this.user = user;
        this.address = address;
    }

    public PersonalInformation(Long id, String legalName, String maidenName, String primaryAddress, String phoneNumber, String birthPlace, String maritalStatus, String occupation, String citizenship, String religion, String militaryStatus, User user, List<Address> address) {
        this.id = id;
        this.legalName = legalName;
        this.maidenName = maidenName;
        this.primaryAddress = primaryAddress;
        this.phoneNumber = phoneNumber;
        this.birthPlace = birthPlace;
        this.maritalStatus = maritalStatus;
        this.occupation = occupation;
        this.citizenship = citizenship;
        this.religion = religion;
        this.militaryStatus = militaryStatus;
        this.user = user;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public String getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(String primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getMilitaryStatus() {
        return militaryStatus;
    }

    public void setMilitaryStatus(String militaryStatus) {
        this.militaryStatus = militaryStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
}