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
    private String current_value;

    @Column(nullable = false)
    private String beneficiary;

    @Column(nullable = false)
    private String contact_info;

    @OneToOne
    private User user;

    @ManyToMany
    @JoinTable(
            name = "users_investment",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "financial_investment_id")})
    private List<User> users;

    public FinancialInvestment() {
    }

    public FinancialInvestment(Long id, String company, String current_value, String beneficiary, String contact_info, User user, List<User> users) {
        this.id = id;
        this.company = company;
        this.current_value = current_value;
        this.beneficiary = beneficiary;
        this.contact_info = contact_info;
        this.user = user;
        this.users = users;
    }

    public FinancialInvestment(String company, String current_value, String beneficiary, String contact_info, User user, List<User> users) {
        this.company = company;
        this.current_value = current_value;
        this.beneficiary = beneficiary;
        this.contact_info = contact_info;
        this.user = user;
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

    public String getCurrent_value() {
        return current_value;
    }

    public void setCurrent_value(String current_value) {
        this.current_value = current_value;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
