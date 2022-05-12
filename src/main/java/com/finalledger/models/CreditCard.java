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

    @ManyToOne
    @JoinColumn(name = "main_user_id")
    private User user;

    public CreditCard(){}

    public CreditCard(Long id, String type, String issuer, User user) {
        this.id = id;
        this.type = type;
        this.issuer = issuer;
        this.user = user;
    }

    public CreditCard(String type, String issuer, User user) {
        this.type = type;
        this.issuer = issuer;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
