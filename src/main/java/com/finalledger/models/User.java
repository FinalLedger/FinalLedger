package com.finalledger.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isMainUser;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_type")
    private AuthenticationType authType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Contact> contacts;

    @Fetch(FetchMode.SELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Documents> documents;

    @Fetch(FetchMode.SELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<CreditCard> creditCards;

    @Fetch(FetchMode.SELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<FinancialInvestment> financialInvestments;

    @Fetch(FetchMode.SELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<InsurancePolicy> insurancePolicy;

    @Fetch(FetchMode.SELECT)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<BankAccounts> bankAccounts;

    public User() {}

    public User(String username, String email, String password, boolean isMainUser) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isMainUser = isMainUser;
    }


    public User(Long id, String username, String email, String password, boolean isMainUser, AuthenticationType authType, List<Contact> contacts, List<Documents> documents, List<CreditCard> creditCards, List<FinancialInvestment> financialInvestments, List<InsurancePolicy> insurancePolicy, List<BankAccounts> bankAccounts) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isMainUser = isMainUser;
        this.authType = authType;
        this.contacts = contacts;
        this.documents = documents;
        this.creditCards = creditCards;
        this.financialInvestments = financialInvestments;
        this.insurancePolicy = insurancePolicy;
        this.bankAccounts = bankAccounts;
    }


    public User(String username, String email, String password, boolean isMainUser, AuthenticationType authType, List<Contact> contacts, List<Documents> documents, List<CreditCard> creditCards, List<FinancialInvestment> financialInvestments, List<InsurancePolicy> insurancePolicy, List<BankAccounts> bankAccounts) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isMainUser = isMainUser;
        this.authType = authType;
        this.contacts = contacts;
        this.documents = documents;
        this.creditCards = creditCards;
        this.financialInvestments = financialInvestments;
        this.insurancePolicy = insurancePolicy;
        this.bankAccounts = bankAccounts;
    }

    public User(User copy) {
        id = copy.id;
        username = copy.username;
        email = copy.email;
        password = copy.password;
        isMainUser = copy.isMainUser;
        authType = copy.authType;
        contacts = copy.contacts;
        documents = copy.documents;
        creditCards = copy.creditCards;
        financialInvestments = copy.financialInvestments;
        insurancePolicy = copy.insurancePolicy;
        bankAccounts = copy.bankAccounts;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMainUser() {
        return isMainUser;
    }

    public void setMainUser(boolean mainUser) {
        isMainUser = mainUser;
    }

    public AuthenticationType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthenticationType authType) {
        this.authType = authType;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Documents> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Documents> documents) {
        this.documents = documents;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public List<FinancialInvestment> getFinancialInvestments() {
        return financialInvestments;
    }

    public void setFinancialInvestments(List<FinancialInvestment> financialInvestments) {
        this.financialInvestments = financialInvestments;
    }

    public List<InsurancePolicy> getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(List<InsurancePolicy> insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }

    public List<BankAccounts> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccounts> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
