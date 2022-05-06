package com.finalledger.models;

import javax.persistence.*;
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

    @Column(nullable = false, unique=true)
    private String password;

    @Column(nullable = false, unique=true)
    private boolean isMainUser;


    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<CreditCard> creditCards;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<FinancialInvestment> financialInvestments;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<InsurancePolicy> insurancePolicy;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<BankAccounts> bankAccounts;

    public User() {}


    public User(String username, String email, String password, boolean isMainUser) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isMainUser = isMainUser;

    }

    public User(Long id, String username, String email, String password, boolean isMainUser) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isMainUser = isMainUser;

    }
    public User(User copy) {
        id = copy.id;
        username = copy.username;
        email = copy.email;
        password = copy.password;
        isMainUser = copy.isMainUser;

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

    public boolean getIsMainUser() {
        return isMainUser;
    }

    public void setMainUser(boolean mainUser) {
        isMainUser = mainUser;
    }

}
