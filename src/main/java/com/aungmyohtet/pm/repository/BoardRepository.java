package com.aungmyohtet.pm.repository;

import java.util.List;

import com.aungmyohtet.pm.entity.Board;

public interface BoardRepository {

    void save(Board board);

    List<Board> findByOrganizationName(String organizationName);

    Integer findBoardMaxNoByOrganizationName(String organizationName);

    Board findOneByOrganizationNameAndBoardNo(String organizationName, int boardNo);

}
