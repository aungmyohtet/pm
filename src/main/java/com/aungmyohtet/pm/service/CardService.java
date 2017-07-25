package com.aungmyohtet.pm.service;

import java.util.List;

import com.aungmyohtet.pm.entity.Card;

public interface CardService {

    List<Card> find(String organizationName, String boardName);

    void findBoardAndAddCard(Card card, int boardNo, String organizationName);

    Card findOne(String organizationName, String boardName, String cardTitle);

}
