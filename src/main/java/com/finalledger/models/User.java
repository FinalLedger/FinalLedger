package com.finalledger.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Documents> documents;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<CreditCard> creditCards;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<FinancialInvestment> financialInvestments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<InsurancePolicy> insurancePolicy;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<BankAccounts> bankAccounts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner_user")
    @JsonBackReference

    private Collection<SiteContact> contactListOwner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "added_user_id")
    @JsonBackReference

    private Collection<SiteContact> contactListEntity;

//   below > user to messages relationship mapped out

    @OneToMany(mappedBy="sender_info")
    @JsonBackReference

    private Collection<Message> senders;

    @OneToMany(mappedBy="receiver_info")
    @JsonBackReference

    private Collection<Message> receivers;

    public User() {}

    public User(String username, String email, String password, boolean isMainUser) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isMainUser = isMainUser;
    }

    public User(Long id, String username, String email, String password, boolean isMainUser, AuthenticationType authType, List<Contact> contacts, List<Documents> documents, List<CreditCard> creditCards, List<FinancialInvestment> financialInvestments, List<InsurancePolicy> insurancePolicy, List<BankAccounts> bankAccounts, Collection<SiteContact> contactListOwner, Collection<SiteContact> contactListEntity, Collection<Message> senders, Collection<Message> receivers) {
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
        this.contactListOwner = contactListOwner;
        this.contactListEntity = contactListEntity;
        this.senders = senders;
        this.receivers = receivers;
    }

    public User(String username, String email, String password, boolean isMainUser, AuthenticationType authType, List<Contact> contacts, List<Documents> documents, List<CreditCard> creditCards, List<FinancialInvestment> financialInvestments, List<InsurancePolicy> insurancePolicy, List<BankAccounts> bankAccounts, Collection<SiteContact> contactListOwner, Collection<SiteContact> contactListEntity, Collection<Message> senders, Collection<Message> receivers) {
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
        this.contactListOwner = contactListOwner;
        this.contactListEntity = contactListEntity;
        this.senders = senders;
        this.receivers = receivers;
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
        contactListOwner = copy.contactListOwner;
        contactListEntity = copy.contactListEntity;
        senders = copy.senders;
        receivers = copy.receivers;
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

    public Collection<SiteContact> getContactListOwner() {
        return contactListOwner;
    }

    public void setContactListOwner(Collection<SiteContact> contactListOwner) {
        this.contactListOwner = contactListOwner;
    }

    public Collection<SiteContact> getContactListEntity() {
        return contactListEntity;
    }

    public void setContactListEntity(Collection<SiteContact> contactListEntity) {
        this.contactListEntity = contactListEntity;
    }

    public Collection<Message> getSenders() {
        return senders;
    }

    public void setSenders(Collection<Message> senders) {
        this.senders = senders;
    }

    public Collection<Message> getReceivers() {
        return receivers;
    }

    public void setReceivers(Collection<Message> receivers) {
        this.receivers = receivers;
    }
}
