package com.finalledger.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class InsurancePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,nullable = false)
    private Long id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String contactInfo;

    @Column(nullable = false)
    private Long currentValue;

    @Column(nullable = false)
    private String beneficiary;

    @ManyToMany
    @JoinTable(
            name="users_policies",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="life_insurance_id")})
    private List<User> users;

    public InsurancePolicy(){}

    public InsurancePolicy(Long id, String company, String contactInfo, Long currentValue, String beneficiary, List<User> users) {
        this.id = id;
        this.company = company;
        this.contactInfo = contactInfo;
        this.currentValue = currentValue;
        this.beneficiary = beneficiary;
        this.users = users;
    }

    public InsurancePolicy(String company, String contactInfo, Long currentValue, String beneficiary, List<User> users) {
        this.company = company;
        this.contactInfo = contactInfo;
        this.currentValue = currentValue;
        this.beneficiary = beneficiary;
        this.users = users;
    }
}
