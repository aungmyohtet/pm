package com.aungmyohtet.pm.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.repository.UserRepository;
import com.aungmyohtet.pm.service.CustomPermissionEvaluatorService;

@Service("permissionEvaluator")
public class CustomPermissionEvaluatorServiceImpl implements CustomPermissionEvaluatorService {

    @Autowired
    private UserRepository userRepository;

    // this is quick implementation
    // to fix it later
    @Override
    public boolean hasPermission(Authentication arg0, Object arg1, Object arg2) {
        if (arg1 instanceof Organization && arg2 instanceof String) {
            Organization organization = (Organization) arg1;
            String permission = (String) arg2;
            
            List<Organization> organizations = userRepository.findOrganizationsCreatedByUser(arg0.getName());
            if (permission.equals("create-project")) {
                for (Organization org : organizations) {
                    if (org.getName().equals(organization.getName())) {
                        return true;
                    }
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasPermission(Authentication arg0, Serializable arg1, String arg2, Object arg3) {
        // TODO Auto-generated method stub
        return false;
    }

}
