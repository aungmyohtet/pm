package com.aungmyohtet.pm.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.OrganizationMember;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.repository.OrganizationMemberRepository;
import com.aungmyohtet.pm.repository.OrganizationRepository;
import com.aungmyohtet.pm.repository.UserRepository;
import com.aungmyohtet.pm.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMemberRepository organizationMemberRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    public void setOrganizationRepository(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void save(Organization organization) {
        organizationRepository.save(organization);
    }

    @Override
    @Transactional
    public void addByUser(Organization organization, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        organizationRepository.save(organization);
        OrganizationMember organizationMember = new OrganizationMember();
        organizationMember.setOrganization(organization);
        organizationMember.setUser(user);
        organizationMember.setRole("owner");
        organizationMemberRepository.add(organizationMember);
    }

    @Override
    @Transactional
    public Organization findByIdFetchingMembers(int id) {
        return organizationRepository.findByIdFetchingMembers(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationMember> findMembersByOrganization(int id) {
        return organizationRepository.findMembersByOrganization(id);
    }

}
