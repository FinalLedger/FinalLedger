package com.finalledger.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserPolicies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="userPolicyId", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long financialInvestmentId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<InsurancePolicy> insurancePolicy;

    public UserPolicies(){}

    public UserPolicies(Long userId, Long financialInvestmentId, List<InsurancePolicy> insurancePolicy){
        this.userId = userId;
        this.financialInvestmentId = financialInvestmentId;
        this.insurancePolicy = insurancePolicy;
    }
    public UserPolicies(User user, Long financialInvestmentId,List<InsurancePolicy> insurancePolicy){
        this.financialInvestmentId = financialInvestmentId;
        this.insurancePolicy = insurancePolicy;
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

    public List<InsurancePolicy> getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(List<InsurancePolicy> insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }
}
