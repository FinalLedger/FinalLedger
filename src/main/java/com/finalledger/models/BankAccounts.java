package com.finalledger.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class BankAccounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String contactInfo;

    @Column(nullable = false)
    private String checkingAccount;

    @Column(nullable = false)
    private String savingAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_user_id")
    private User user;

    public BankAccounts(){}

    public BankAccounts(Long id, String company, String contactInfo, String checkingAccount, String savingAccount, User user) {
        this.id = id;
        this.company = company;
        this.contactInfo = contactInfo;
        this.checkingAccount = checkingAccount;
        this.savingAccount = savingAccount;
        this.user = user;
    }

    public BankAccounts(String company, String contactInfo, String checkingAccount, String savingAccount, User user) {
        this.company = company;
        this.contactInfo = contactInfo;
        this.checkingAccount = checkingAccount;
        this.savingAccount = savingAccount;
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

    public String getCheckingAccount() {
        return checkingAccount;
    }

    public void setCheckingAccount(String checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public String getSavingAccount() {
        return savingAccount;
    }

    public void setSavingAccount(String savingAccount) {
        this.savingAccount = savingAccount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
