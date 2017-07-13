package com.aungmyohtet.pm.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Card;
import com.aungmyohtet.pm.entity.OrganizationMember;
import com.aungmyohtet.pm.repository.CardRepository;

@Repository
public class CardRepositoryImpl implements CardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Card> find(String organizationName, int boardNo) {
        Query query = entityManager.createQuery("SELECT c FROM Card c " + "JOIN c.board b " + "JOIN b.organization o " + "WHERE o.name = ? AND b.no = ?", Card.class);
        query.setParameter(1, organizationName);
        query.setParameter(2, boardNo);
        List<Card> cards = query.getResultList();
        List<Card> resultCards = new ArrayList<Card>();
        for (Card card : cards) {
            if (card.getStartShownDate().before(card.getCreatedDate()) && card.getLastShownDate().after(card.getCreatedDate())) {
                resultCards.add(card);
            }
        }
        return resultCards;
    }

    @Override
    public void save(Card card) {
        if (card.getId() == null) {
            entityManager.persist(card);
        } else {
            entityManager.merge(card);
        }
    }

    @Override
    public Card find(String organizationName, int boardNo, String cardTitle) {
        Query query = entityManager.createQuery("SELECT c FROM Card c " + "JOIN c.board b " + "JOIN b.organization o " + "WHERE o.name = ? AND b.no = ? AND c.title = ?",
                Card.class);
        query.setParameter(1, organizationName);
        query.setParameter(2, boardNo);
        query.setParameter(3, cardTitle);
        return (Card) query.getSingleResult();
    }
}
