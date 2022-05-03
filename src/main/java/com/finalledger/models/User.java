package com.finalledger.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isMainUser;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserCreditCards> cards;

    public User() {}

    public User(String username, String email, String password, boolean isMainUser, List<User> users) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isMainUser = isMainUser;
        this.users = users;
    }

    public User(Long id, String username, String email, String password, boolean isMainUser, List<User> users) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isMainUser = isMainUser;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMainUser() {
        return isMainUser;
    }

    public void setMainUser(boolean mainUser) {
        isMainUser = mainUser;
    }
}
