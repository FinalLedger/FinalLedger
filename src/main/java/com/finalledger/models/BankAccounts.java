package com.finalledger.models;

import javax.persistence.*;

@Entity
public class BankAccounts {
    @Id
    @GeneratedValue
    @Column(name= "bankAccountId", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String contactInfo;

    @Column(nullable = false)
    private boolean checkingAccount;

    @Column(nullable = false)
    private boolean savingAccount;

    @ManyToOne
    @JoinColumn(name="UserAccountId")
    private User user;

    public BankAccounts(){}

    public BankAccounts(Long id,String company, String contactInfo,User user , boolean checkingAccount , boolean savingAccount){
        this.id = id;
        this.company = company;
        this.contactInfo = contactInfo;
        this.user = user;
        this.checkingAccount = checkingAccount;
        this.savingAccount = savingAccount;

    }
    public BankAccounts(String company, String contactInfo, User user , boolean checkingAccount , boolean savingAccount){
        this.company = company;
        this.contactInfo = contactInfo;
        this.user = user;
        this.checkingAccount = checkingAccount;
        this.savingAccount = savingAccount;
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

    public boolean isCheckingAccount() {
        return checkingAccount;
    }

    public void setCheckingAccount(boolean checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public boolean isSavingAccount() {
        return savingAccount;
    }

    public void setSavingAccount(boolean savingAccount) {
        this.savingAccount = savingAccount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
