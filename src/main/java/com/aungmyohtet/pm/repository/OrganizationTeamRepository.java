package com.aungmyohtet.pm.repository;

import java.util.List;

import com.aungmyohtet.pm.entity.OrganizationTeam;

public interface OrganizationTeamRepository {

    void save(OrganizationTeam organizationTeam);

    OrganizationTeam findById(int id);

    List<OrganizationTeam> findTeamByOrganizationId(int organizationId);

}
