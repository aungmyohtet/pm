package com.aungmyohtet.pm.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Card;
import com.aungmyohtet.pm.repository.BoardRepository;
import com.aungmyohtet.pm.repository.CardRepository;
import com.aungmyohtet.pm.service.CardService;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Override
    @Transactional
    public List<Card> find(String organizationName, String boardName) {
        return cardRepository.find(organizationName, boardName);
    }

    @Override
    @Transactional
    public void findBoardAndAddCard(Card card, int boardNo, String organizationName) {
        Board board = boardRepository.findOneByOrganizationNameAndBoardNo(organizationName, boardNo);
        Date date = new Date();
        card.setBoard(board);
        card.setCreatedDate(date);
        cardRepository.save(card);
    }

    @Override
    @Transactional
    public Card findOne(String organizationName, String boardName, String cardTitle) {
        return cardRepository.findOne(organizationName, boardName, cardTitle);
    }

}
