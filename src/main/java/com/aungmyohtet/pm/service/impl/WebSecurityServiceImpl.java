package com.aungmyohtet.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.repository.UserRepository;
import com.aungmyohtet.pm.service.WebSecurityService;

import java.util.List;

@Service("webSecurity")
public class WebSecurityServiceImpl implements WebSecurityService{


    @Autowired
    private UserRepository userRepository;

    // This is quick implementation to check results
    // To update it later
    @Override
    public boolean canAddMemberToOrganization(Authentication authentication, String organizationName) {
        List<Organization> organizations = userRepository.findOrganizationsCreatedByUser(authentication.getName());
        for (Organization organization : organizations) {
            if (organization.getName().equals(organizationName)) {
                return true;
            }
        }
        return false;
    }

}
