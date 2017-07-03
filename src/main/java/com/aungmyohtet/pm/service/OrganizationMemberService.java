package com.aungmyohtet.pm.service;

public interface OrganizationMemberService {

    void addMemberToOrganization(String userEmail, int organizationId);

    void addMemberToOrganization(String userEmail, String organizationName);
}
