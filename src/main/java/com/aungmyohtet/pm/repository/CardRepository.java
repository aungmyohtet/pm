package com.aungmyohtet.pm.repository;

import java.util.List;

import com.aungmyohtet.pm.entity.Card;

public interface CardRepository {

    List<Card> find(String organizationName, String boardName);

    void save(Card card);

    Card findOne(String organizationName, String boardName, String cardTitle);

}
