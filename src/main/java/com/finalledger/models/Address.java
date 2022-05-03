package com.finalledger.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String propertyType;

    @Column(nullable = false)
    private String streetAddress;

    @Column(nullable = false)
    private String streetAddress2;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private int zipcode;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable (
            name = "contact_addresses",
            joinColumns={@JoinColumn(name = "contact_id")},
            inverseJoinColumns={@JoinColumn(name = "address_id")}
    )
    private List<Address> addresses;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable (
            name = "personal_addresses",
            joinColumns={@JoinColumn(name )}
    )

    public Address() {}

    public Address(String propertyType, String streetAddress, String streetAddress2, String city, String state, int zipcode) {
        this.propertyType = propertyType;
        this.streetAddress = streetAddress;
        this.streetAddress2 = streetAddress2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public Address(Long id, String propertyType, String streetAddress, String streetAddress2, String city, String state, int zipcode) {
        this.id = id;
        this.propertyType = propertyType;
        this.streetAddress = streetAddress;
        this.streetAddress2 = streetAddress2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
}
