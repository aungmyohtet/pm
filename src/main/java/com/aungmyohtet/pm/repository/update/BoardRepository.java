package com.aungmyohtet.pm.repository.update;

import java.util.List;

import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Organization;

public interface BoardRepository {

    void save(Board board);

    List<Board> findAll();

    List<Board> findByOrganization(Organization organization);

    List<Board> findByOrganizationName(String organizationName);

    void delete(Board board);

    List<Board> findByName(String name);

    List<Board> findByNameAndOrganization(String name, Organization organization);

    List<Board> findByNameAndOrganizationName(String name, String organizationName);

}
