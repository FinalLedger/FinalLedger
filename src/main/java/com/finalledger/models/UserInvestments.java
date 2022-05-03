package com.finalledger.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserInvestments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userFinancial_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long financialInvestmentId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<FinancialInvestment> financialInvestments;

    public UserInvestments(){}

    public UserInvestments(Long id,Long financialInvestmentId){
        this.id = id;
        this.financialInvestmentId = financialInvestmentId;
    }
    public UserInvestments(Long financialInvestmentId){
        this.financialInvestmentId = financialInvestmentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFinancialInvestmentId() {
        return financialInvestmentId;
    }

    public void setFinancialInvestmentId(Long financialInvestmentId) {
        this.financialInvestmentId = financialInvestmentId;
    }

    public List<FinancialInvestment> getFinancialInvestments() {
        return financialInvestments;
    }

    public void setFinancialInvestments(List<FinancialInvestment> financialInvestments) {
        this.financialInvestments = financialInvestments;
    }
}
