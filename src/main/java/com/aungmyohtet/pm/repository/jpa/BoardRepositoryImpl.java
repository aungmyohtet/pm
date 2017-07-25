package com.aungmyohtet.pm.repository.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.repository.BoardRepository;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Board board) {
        if (board.getId() == null) {
            entityManager.persist(board);
        } else {
            entityManager.merge(board);
        }
    }

    @Override
    public List<Board> findByOrganizationName(String organizationName) {

        Query query = entityManager.createQuery("SELECT b FROM Board b " + "JOIN b.organization o " + "WHERE o.name = ? ", Board.class);
        query.setParameter(1, organizationName);
        return query.getResultList();
    }

    @Override
    public Integer findBoardMaxNoByOrganizationName(String organizationName) {

        Query query = entityManager.createQuery("SELECT MAX(b.no) FROM Board b " + "JOIN b.organization o " + "WHERE o.name = ?");
        query.setParameter(1, organizationName);

        Integer no = 0;
        try {
            no = (Integer) query.getSingleResult();
        } catch (Exception e) {

        }
        return no;
    }

    @Override
    public Board findOneByOrganizationNameAndBoardNo(String organizationName, int boardNo) {
        Query query = entityManager.createQuery("SELECT b FROM Board b WHERE b.organization.name = ? AND b.no = ?");
        query.setParameter(1, organizationName);
        query.setParameter(2, boardNo);
        Board board = null;
        try {
            return (Board) query.getSingleResult();
        } catch (Exception e) {

        }
        return board;

    }
}
