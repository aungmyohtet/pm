package com.aungmyohtet.pm.service;

import com.aungmyohtet.pm.entity.Board;

public interface BoardService {

    void addBoardToOrganization(Board board, String organizationName);
}
