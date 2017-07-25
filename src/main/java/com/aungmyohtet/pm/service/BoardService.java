package com.aungmyohtet.pm.service;

import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Organization;

public interface BoardService {

    void addBoardToOrganization(Board board, Organization organization);
}
