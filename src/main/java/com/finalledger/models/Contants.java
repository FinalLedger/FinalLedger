package com.finalledger.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "contacts")
public class Contants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String relationship;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String primaryAddress;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "contactAddresses")
    private List<Address> address;

    @OneToOne
    @JoinColumn(name = "main_user_id")

    private User user;

    public Contants() {
    }

    public Contants(Long id, String relationship, String firstName, String lastName, String phoneNumber, String email, String primaryAddress, List<Address> address, User user) {
        this.id = id;
        this.relationship = relationship;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.primaryAddress = primaryAddress;
        this.address = address;
        this.user = user;
    }

    public Contants(String relationship, String firstName, String lastName, String phoneNumber, String email, String primaryAddress, List<Address> address, User user) {
        this.relationship = relationship;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.primaryAddress = primaryAddress;
        this.address = address;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(String primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
