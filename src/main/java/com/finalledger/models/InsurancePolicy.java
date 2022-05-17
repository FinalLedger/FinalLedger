package com.finalledger.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class InsurancePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,nullable = false)
    private Long id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String contactInfo;

    @Column(nullable = false)
    private String currentValue;

    @Column(nullable = false)
    private String beneficiary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_user_id")
    private User user;

    public InsurancePolicy(){}

    public InsurancePolicy(Long id, String company, String contactInfo, String currentValue, String beneficiary, User user) {
        this.id = id;
        this.company = company;
        this.contactInfo = contactInfo;
        this.currentValue = currentValue;
        this.beneficiary = beneficiary;
        this.user = user;
    }

    public InsurancePolicy(String company, String contactInfo, String currentValue, String beneficiary, User user) {
        this.company = company;
        this.contactInfo = contactInfo;
        this.currentValue = currentValue;
        this.beneficiary = beneficiary;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
