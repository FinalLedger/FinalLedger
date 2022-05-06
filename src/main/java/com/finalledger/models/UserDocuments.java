package com.finalledger.models;

import javax.persistence.*;

@Entity
public class UserDocuments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String document_upload;

    @OneToOne
    @JoinColumn(name = "main_user_id")
    private User user;

    public UserDocuments(){}

    public UserDocuments(Long id,String title, String document_upload,User user){
        this.id= id;
        this.title = title;
        this.document_upload = document_upload;
        this.user =user;
    }

    public UserDocuments(String title, String document_upload,User user){
        this.title = title;
        this.document_upload = document_upload;
        this.user =user;
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

    public String getDocumentUpload() {
        return document_upload;
    }

    public void setDocumentUpload(String documentUpload) {
        this.document_upload = documentUpload;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
