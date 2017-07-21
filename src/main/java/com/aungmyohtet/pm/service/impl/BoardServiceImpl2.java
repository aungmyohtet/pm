package com.aungmyohtet.pm.service.impl;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.dto.BoardDto;
import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Card;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.repository.update.BoardRepository;
import com.aungmyohtet.pm.service.BoardService2;

@Service
public class BoardServiceImpl2 implements BoardService2 {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Set<Card> findCardsByBoard(Board board) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addCardToBoard(Card card, Board board) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeCardFromBoard(Card card, Board board) {
        // TODO Auto-generated method stub

    }

    // The following methods are duplicate methods of repository methods.
    // Consider to remove them or move to other service classes later

    @Override
    @Transactional
    public void save(Board board) {
        this.boardRepository.save(board);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> findAll() {
        return this.boardRepository.findAll();
    }

    @Override
    public List<Board> findByOrganization(Organization organization) {
        return this.boardRepository.findByOrganization(organization);
    }

    @Override
    public List<Board> findByOrganizationName(String organizationName) {
        return this.boardRepository.findByOrganizationName(organizationName);
    }

    @Override
    public void delete(Board board) {
        this.boardRepository.delete(board);
    }

    @Override
    public List<Board> findByName(String name) {
        return this.boardRepository.findByName(name);
    }

    @Override
    public List<Board> findByNameAndOrganization(String name, Organization organization) {
        return this.boardRepository.findByNameAndOrganization(name, organization);
    }

    @Override
    public List<Board> findByNameAndOrganizationName(String name, String organizationName) {
        return this.boardRepository.findByNameAndOrganizationName(name, organizationName);
    }

    @Override
    public BoardDto convertToDto(Board board) {
        return modelMapper.map(board, BoardDto.class);
    }

}
