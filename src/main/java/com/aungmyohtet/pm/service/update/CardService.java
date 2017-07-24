package com.aungmyohtet.pm.service.update;

import java.util.List;

import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Card;

public interface CardService {

    // wrap normal repository methods
    void save(Card card);

    void delete(Card card);

    List<Card> findAll();

    List<Card> findByBoard(Board board);

    Card findById(int id);
}
