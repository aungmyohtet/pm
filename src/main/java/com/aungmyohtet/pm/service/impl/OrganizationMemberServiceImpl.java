package com.aungmyohtet.pm.service.impl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.OrganizationMember;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.repository.OrganizationMemberRepository;
import com.aungmyohtet.pm.repository.OrganizationRepository;
import com.aungmyohtet.pm.repository.RoleRepository;
import com.aungmyohtet.pm.repository.UserRepository;
import com.aungmyohtet.pm.service.OrganizationMemberService;

@Service
public class OrganizationMemberServiceImpl implements OrganizationMemberService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizationMemberRepository organizationMemberRepository;

    @Override
    @Transactional
    public void addMemberToOrganization(String userEmail, int organizationId) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return;
        }
        Organization organization  = organizationRepository.findById(organizationId);
        OrganizationMember organizationMember = new OrganizationMember();
        organizationMember.setOrganization(organization);
        organizationMember.setUser(user);
        organizationMember.setRole(roleRepository.findByName("DEVELOPER"));
        organizationMemberRepository.add(organizationMember);
    }

}
