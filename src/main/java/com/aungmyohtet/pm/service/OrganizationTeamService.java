package com.aungmyohtet.pm.service;

import java.util.List;

import com.aungmyohtet.pm.entity.OrganizationTeam;

public interface OrganizationTeamService {

    void save(OrganizationTeam organizationTeam);

    OrganizationTeam findById(int id);

    List<OrganizationTeam> findTeamByOrganizationId(int organizationId);

}
