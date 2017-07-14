package com.aungmyohtet.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.entity.OrganizationTeam;
import com.aungmyohtet.pm.repository.OrganizationTeamRepository;
import com.aungmyohtet.pm.service.OrganizationTeamService;

@Service
public class OgranizationTeamServiceImpl implements OrganizationTeamService {

    @Autowired
    private OrganizationTeamRepository organizationTeamRepository;

    public void setOrganizationTeamRepository(OrganizationTeamRepository organizationTeamRepository) {
        this.organizationTeamRepository = organizationTeamRepository;
    }

    @Override
    @Transactional
    public void save(OrganizationTeam organizationTeam) {
        organizationTeamRepository.save(organizationTeam);
    }

    @Override
    @Transactional
    public OrganizationTeam findById(int id) {
        return organizationTeamRepository.findById(id);
    }

    @Override
    @Transactional
    public List<OrganizationTeam> findTeamByOrganizationId(int organizationId) {
        return organizationTeamRepository.findTeamByOrganizationId(organizationId);
    }

}
