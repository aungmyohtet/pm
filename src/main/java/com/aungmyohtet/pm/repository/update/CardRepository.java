package com.aungmyohtet.pm.repository.update;

import java.util.List;

import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Card;

public interface CardRepository {

    void save(Card card);

    void delete(Card card);

    List<Card> findAll();

    List<Card> findByBoard(Board board);
}
