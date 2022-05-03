package com.finalledger.models;

import javax.persistence.*;

@Entity
public class FinancialInvestment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="financialInvestment_Id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private Long current_value;

    @Column(nullable = false)
    private String beneficiary;

    @Column(nullable = false)
    private String contact_info;

    @ManyToOne
    @JoinColumn(name="userFinancial_id")
    private User user;

    public FinancialInvestment(){}

    public FinancialInvestment(Long id, String company, String beneficiary,User user, String contact_info){
        this.id = id;
        this.company = company;
        this.beneficiary = beneficiary;
        this.contact_info = contact_info;
        this.user = user;
    }
    public FinancialInvestment( String company, String beneficiary,User user, String contact_info){
        this.company = company;
        this.beneficiary = beneficiary;
        this.contact_info = contact_info;
        this.user = user;
    }
}
