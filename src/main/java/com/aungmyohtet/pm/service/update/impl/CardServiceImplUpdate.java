package com.aungmyohtet.pm.service.update.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Card;
import com.aungmyohtet.pm.repository.update.CardRepository;
import com.aungmyohtet.pm.service.update.CardService;

@Service
public class CardServiceImplUpdate implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    @Transactional
    public void save(Card card) {
        cardRepository.save(card);
    }

    @Override
    @Transactional
    public void delete(Card card) {
        cardRepository.delete(card);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Card> findByBoard(Board board) {
        return cardRepository.findByBoard(board);
    }

    @Override
    @Transactional(readOnly = true)
    public Card findById(int id) {
        return cardRepository.findById(id);
    }

}
