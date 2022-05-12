package com.finalledger.models;

import javax.persistence.*;

@Entity
@Table(name="documents")
public class Documents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String title;

    @Column
    private String document_upload;

    @ManyToOne
    @JoinColumn(name = "main_user_id")
    private User user;

    public Documents(){}

    public Documents(Long id, String title, String document_upload, User user){
        this.id= id;
        this.title = title;
        this.document_upload = document_upload;
        this.user = user;
    }

    public Documents(String title, String document_upload, User user){
        this.title = title;
        this.document_upload = document_upload;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDocument_upload() {
        return document_upload;
    }

    public void setDocument_upload(String document_upload) {
        this.document_upload = document_upload;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
