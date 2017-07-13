package com.aungmyohtet.pm.repository;

import java.util.List;

import com.aungmyohtet.pm.entity.Card;

public interface CardRepository {

    List<Card> find(String organizationName, int boardNo);

    void save(Card card);

    Card find(String organizationName, int boardNo, String cardTitle);

}
