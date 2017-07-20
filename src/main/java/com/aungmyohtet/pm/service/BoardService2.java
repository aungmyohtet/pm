package com.aungmyohtet.pm.service;

import java.util.Set;

import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Card;

public interface BoardService2 {

    Set<Card> findCardsByBoard(Board board);

    void addCardToBoard(Card card, Board board);

    void removeCardFromBoard(Card card, Board board);

}
