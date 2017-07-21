package com.aungmyohtet.pm.repository.update.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.repository.update.BoardRepository;

@Repository
public class BoardRepositoryImplUpdate implements BoardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Board board) {
        if (board.getId() == null) {
            this.entityManager.persist(board);
        } else {
            this.entityManager.merge(board);
        }
    }

    @Override
    public List<Board> findAll() {
        Query query = this.entityManager.createQuery("select b from Board b", Board.class);
        return query.getResultList();
    }

    @Override
    public List<Board> findByOrganization(Organization organization) {
        Query query = this.entityManager.createQuery("select b from Board b where b.organization = ?", Board.class);
        query.setParameter(1, organization);
        return query.getResultList();
    }

    @Override
    public List<Board> findByOrganizationName(String organizationName) {
        Query query = this.entityManager.createQuery("select b from Board b where b.organization.name = ?", Board.class);
        query.setParameter(1, organizationName);
        return query.getResultList();
    }

    @Override
    public void delete(Board board) {
        this.entityManager.remove(board);
    }

    @Override
    public List<Board> findByName(String name) {
        Query query = this.entityManager.createQuery("select b from Board b where b.name = ?", Board.class);
        query.setParameter(1, name);
        return query.getResultList();
    }

    @Override
    public Board findByNameAndOrganization(String name, Organization organization) {
        Query query = this.entityManager.createQuery("select b from Board b where b.name = ? and b.organization = ?", Board.class);
        query.setParameter(1, name);
        query.setParameter(2, organization);
        Board board = null;
        try {
            board = (Board) query.getSingleResult();
        } catch (Exception e) {
            // to implement
        }
        return board;
    }

    @Override
    public Board findByNameAndOrganizationName(String name, String organizationName) {
        Query query = this.entityManager.createQuery("select b from Board b where b.name = ? and b.organization.name = ?", Board.class);
        query.setParameter(1, name);
        query.setParameter(2, organizationName);
        Board board = null;
        try {
            board = (Board) query.getSingleResult();
        } catch (Exception exception) {
            // to implement
        }
        return board;
    }

}
