package com.aungmyohtet.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.repository.BoardRepository;
import com.aungmyohtet.pm.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    @Transactional
    public void addBoardToOrganization(Board board, Organization organization) {
        board.setOrganization(organization);
        boardRepository.save(board);
    }
}
