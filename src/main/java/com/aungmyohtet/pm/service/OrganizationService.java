package com.aungmyohtet.pm.service;

import java.util.List;
import java.util.Set;

import com.aungmyohtet.pm.dto.OrganizationDto;
import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.OrganizationMember;
import com.aungmyohtet.pm.entity.Project;

public interface OrganizationService {

    void save(Organization organization);

    void addByUser(Organization organization, String userEmail);

    Organization findByIdFetchingMembers(int id);

    List<OrganizationMember> findMembersByOrganization(int id);

    List<Project> findProjectsByOrganization(int id);

    OrganizationDto convertToDto(Organization organization);

    List<Project> findProjectsByOrganization(String name);

    List<Board> findBoardsByOrganization(String organizationName);
}
