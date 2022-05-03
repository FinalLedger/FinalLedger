package com.finalledger.models;

import javax.persistence.*;

@Entity
class UserCreditCards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id", nullable = false)
    private Long id;


    @Column(nullable = false)
    private Long cardId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public UserCreditCards(){}

    public UserCreditCards(Long id,User user,Long cardId){
        this.id = id;
        this.user= user;
        this.cardId = cardId;
    }
    public UserCreditCards(User user,Long cardId){
        this.user = user;
        this.cardId = cardId;
    }

}
