package com.finalledger.models;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name="user_accounts",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="bank_id")})
    private List<User> users;

    public BankAccounts(){}

    public BankAccounts(Long id, String company, String contactInfo, boolean checkingAccount, boolean savingAccount, List<User> users) {
        this.id = id;
        this.company = company;
        this.contactInfo = contactInfo;
        this.checkingAccount = checkingAccount;
        this.savingAccount = savingAccount;
        this.users = users;
    }

    public BankAccounts(String company, String contactInfo, boolean checkingAccount, boolean savingAccount, List<User> users) {
        this.company = company;
        this.contactInfo = contactInfo;
        this.checkingAccount = checkingAccount;
        this.savingAccount = savingAccount;
        this.users = users;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
