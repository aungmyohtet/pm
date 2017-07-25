package com.aungmyohtet.pm.service;

import java.util.List;
import java.util.Set;

import com.aungmyohtet.pm.dto.BoardDto;
import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Card;
import com.aungmyohtet.pm.entity.Organization;

public interface BoardService2 {

    Set<Card> findCardsByBoard(Board board);

    void addCardToBoard(Card card, Board board);

    void removeCardFromBoard(Card card, Board board);

    List<Card> getCards(Board board);

    // separator

    void save(Board board);

    List<Board> findAll();

    List<Board> findByOrganization(Organization organization);

    List<Board> findByOrganizationName(String organizationName);

    void delete(Board board);

    List<Board> findByName(String name);

    Board findByNameAndOrganization(String name, Organization organization);

    Board findByNameAndOrganizationName(String name, String organizationName);

    BoardDto convertToDto(Board board);

}
