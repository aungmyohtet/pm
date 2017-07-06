package com.aungmyohtet.pm.repository;

import java.util.List;
import java.util.Set;

import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.OrganizationMember;
import com.aungmyohtet.pm.entity.Project;

public interface OrganizationRepository {

    void save(Organization organization);

    Organization findById(int id);

    Organization findByIdFetchingMembers(int id);

    List<OrganizationMember> findMembersByOrganization(int id);

    List<Project> findProjectsByOrganization(int id);

    Organization findByName(String name);

    Organization findByNameFetchingMembers(String name);

    List<OrganizationMember> findMembersByOrganizationName(String name);

    List<Project> findProjectsByOrganizationName(String name);

    List<Board> findBoardsByOrganizationName(String organizationName);
}
