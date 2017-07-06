package com.aungmyohtet.pm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.repository.BoardRepository;
import com.aungmyohtet.pm.repository.OrganizationRepository;
import com.aungmyohtet.pm.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    @Transactional
    public void addBoardToOrganization(Board board, String organizationName) {
        Organization organization = organizationRepository.findByName(organizationName);
        board.setOrganization(organization);
        List<Board> boards = boardRepository.find(organizationName);
        if (boards == null || boards.size() == 0) {
            board.setNo(1);
        } else {
            int currentMaxBoardNo = boardRepository.findBoardMaxNoByOrganizationName(organizationName);
            board.setNo(currentMaxBoardNo + 1);
        }
        boardRepository.save(board);
    }
}
