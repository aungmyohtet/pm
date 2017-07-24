package com.aungmyohtet.pm.service.update.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.dto.BoardDto;
import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Card;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.repository.update.BoardRepository;
import com.aungmyohtet.pm.repository.update.CardRepository;
import com.aungmyohtet.pm.service.update.BoardService;

@Service
public class BoardServiceImplUpdate implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CardRepository cardRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Card> getCards(Board board) {
        return cardRepository.findByBoard(board);
    }

    @Override
    @Transactional
    public void addCard(Board board, Card card) {
        card.setBoard(board);
        cardRepository.save(card);
    }

    @Override
    public void removeCard(Board board, Card card) {
        // TODO Auto-generated method stub

    }

    @Override
    public void transferCard(Card card, Board from, Board to) {
        // TODO Auto-generated method stub

    }

    @Override
    @Transactional
    public void save(Board board) {
        boardRepository.save(board);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> findByOrganization(Organization organization) {
        return boardRepository.findByOrganization(organization);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> findByOrganizationName(String organizationName) {
        return boardRepository.findByOrganizationName(organizationName);
    }

    @Override
    @Transactional
    public void delete(Board board) {
        boardRepository.delete(board);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> findByName(String name) {
        return boardRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Board findByNameAndOrganization(String name, Organization organization) {
        return boardRepository.findByNameAndOrganization(name, organization);
    }

    @Override
    @Transactional(readOnly = true)
    public Board findByNameAndOrganizationName(String name, String organizationName) {
        return boardRepository.findByNameAndOrganizationName(name, organizationName);
    }

    @Override
    public BoardDto convertToDto(Board board) {
        return modelMapper.map(board, BoardDto.class);
    }

}
