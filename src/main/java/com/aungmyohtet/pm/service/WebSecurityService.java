package com.aungmyohtet.pm.service;

import org.springframework.security.core.Authentication;

public interface WebSecurityService {

    boolean canAddMemberToOrganization(Authentication authentication, String organizationName);

}
