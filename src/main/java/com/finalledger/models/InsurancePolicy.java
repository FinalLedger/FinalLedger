package com.finalledger.models;

import javax.persistence.*;

@Entity
public class InsurancePolicy {
    @Id
    @GeneratedValue
    @Column(name = "insurancePolicyId" ,nullable = false)
    private Long id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String contactInfo;

    @Column(nullable = false)
    private Long currentValue;

    @Column(nullable = false)
    private String beneficiary;

    @ManyToOne
    @JoinColumn(name="userPolicyId")
    private User user;

    public InsurancePolicy(){}

    public InsurancePolicy(Long id,String company, String contactInfo, Long currentValue, String beneficiary,User user){
        this.id = id;
        this.company = company;
        this.contactInfo = contactInfo;
        this.currentValue = currentValue;
        this.beneficiary = beneficiary;
        this.user = user;
    }

    public InsurancePolicy(String company, String contactInfo, Long currentValue, String beneficiary,User user){
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

    public Long getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Long currentValue) {
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
