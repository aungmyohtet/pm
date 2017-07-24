package com.aungmyohtet.pm.service.update;

import java.util.List;

import com.aungmyohtet.pm.dto.BoardDto;
import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Card;
import com.aungmyohtet.pm.entity.Organization;

public interface BoardService {

    List<Card> getCards(Board board);

    void addCard(Board board, Card card);

    void removeCard(Board board, Card card);

    void transferCard(Card card, Board from, Board to);

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
