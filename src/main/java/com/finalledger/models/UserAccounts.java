package com.finalledger.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserAccounts {
    @Id
    @GeneratedValue
    @Column(name = "userAccountId", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long bankId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<BankAccounts> bankAccounts;

    public UserAccounts(){}

    public UserAccounts(Long id, List<BankAccounts> bankAccounts, Long bankId){
        this.id = id;
        this.bankAccounts = bankAccounts;
        this.bankId = bankId;
    }
    public UserAccounts(List<BankAccounts> bankAccounts, Long bankId){
        this.bankAccounts = bankAccounts;
        this.bankId = bankId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public List<BankAccounts> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccounts> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
