package com.aungmyohtet.pm.service;

import com.aungmyohtet.pm.entity.Organization;

public interface OrganizationService {

    void save(Organization organization);
 
    void addByUser(Organization organization, String userEmail);
}
