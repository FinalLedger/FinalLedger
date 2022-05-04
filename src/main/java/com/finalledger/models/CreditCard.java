package com.finalledger.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable=false)
    private String issuer;

    @ManyToMany
    @JoinTable(
            name="User_Credit_Cards",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="card_id")})
    private List<User> users;

    public CreditCard(){}

    public CreditCard(Long id, String type, String issuer, List<User> users) {
        this.id = id;
        this.type = type;
        this.issuer = issuer;
        this.users = users;

    }
    public CreditCard( String type, String issuer, List<User> users) {
        this.type = type;
        this.issuer = issuer;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
