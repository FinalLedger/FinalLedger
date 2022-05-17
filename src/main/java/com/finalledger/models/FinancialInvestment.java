package com.finalledger.models;

import com.finalledger.repositories.FinancialInvestmentRepository;
import com.finalledger.repositories.UserRepository;

import javax.persistence.*;
import java.util.List;

@Entity
public class FinancialInvestment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String currentValue;

    @Column(nullable = false)
    private String beneficiary;

    @Column(nullable = false)
    private String contactInfo;

    @ManyToOne
    @JoinColumn(name = "main_user_id")
    private User user;

    public FinancialInvestment() {
    }

    public FinancialInvestment(Long id, String company, String currentValue, String beneficiary, String contactInfo, User user) {
        this.id = id;
        this.company = company;
        this.currentValue = currentValue;
        this.beneficiary = beneficiary;
        this.contactInfo = contactInfo;
        this.user = user;
    }

    public FinancialInvestment(String company, String currentValue, String beneficiary, String contactInfo, User user) {
        this.company = company;
        this.currentValue = currentValue;
        this.beneficiary = beneficiary;
        this.contactInfo = contactInfo;
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

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
