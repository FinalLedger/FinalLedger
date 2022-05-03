package com.finalledger.models;

import javax.persistence.*;
import java.util.List;

@Entity
class UserCreditCards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="userCreditCard_id", nullable = false)
    private Long id;


    @Column(nullable = false)
    private Long cardId;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<CreditCard> creditCards;

    public UserCreditCards(){}

    public UserCreditCards(Long id,Long cardId, List<CreditCard> creditCards){
        this.creditCards = creditCards;
        this.id = id;
        this.cardId = cardId;
    }

    public UserCreditCards(Long cardId,  List<CreditCard> creditCards){
        this.creditCards = creditCards;
        this.cardId = cardId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }
}
