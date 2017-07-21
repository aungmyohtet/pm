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

    void save(Board board);

    List<Board> findAll();

    List<Board> findByOrganization(Organization organization);

    List<Board> findByOrganizationName(String organizationName);

    void delete(Board board);

    List<Board> findByName(String name);

    List<Board> findByNameAndOrganization(String name, Organization organization);

    List<Board> findByNameAndOrganizationName(String name, String organizationName);

    BoardDto convertToDto(Board board);

}
