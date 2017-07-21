package com.aungmyohtet.pm.repository.update.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Card;
import com.aungmyohtet.pm.repository.update.CardRepository;

@Repository
public class CardRepositoryImplUpdate implements CardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Card card) {
        if (card == null) {
            this.entityManager.persist(card);
        } else {
            this.entityManager.merge(card);
        }
    }

    @Override
    public void delete(Card card) {
        this.entityManager.remove(card);
    }

    @Override
    public List<Card> findAll() {
        Query query = this.entityManager.createQuery("select c from Card c", Card.class);
        return query.getResultList();
    }

    @Override
    public List<Card> findByBoard(Board board) {
        Query query = this.entityManager.createQuery("select c from Card c where c.board = ?", Card.class);
        query.setParameter(1, board);
        return query.getResultList();
    }

}
