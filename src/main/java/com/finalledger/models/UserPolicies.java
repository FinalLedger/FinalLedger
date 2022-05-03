package com.finalledger.models;

import javax.persistence.*;

@Entity
public class UserPolicies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long financialInvestmentId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public UserPolicies(){}

    public UserPolicies(Long userId, User user, Long financialInvestmentId){
        this.userId = userId;
        this.user = user;
        this.financialInvestmentId = financialInvestmentId;
    }
    public UserPolicies(User user, Long financialInvestmentId){
        this.user =user;
        this.financialInvestmentId = financialInvestmentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFinancialInvestmentId() {
        return financialInvestmentId;
    }

    public void setFinancialInvestmentId(Long financialInvestmentId) {
        this.financialInvestmentId = financialInvestmentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
