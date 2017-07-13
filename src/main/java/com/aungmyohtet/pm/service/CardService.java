package com.aungmyohtet.pm.service;

import java.util.List;

import com.aungmyohtet.pm.entity.Card;

public interface CardService {

    List<Card> findByOrganizationNameAndBoardNo(String organizationName, int boardNo);

    void findBoardAndAddCard(Card card, int boardNo, String organizationName);

    Card find(String organizationName, int boardNo, String cardTitle);

}
