package com.aungmyohtet.pm.repository.update;

import java.util.List;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.OrganizationMember;

public interface OrganizationMemberRepository {

    List<OrganizationMember> findByOrganization(Organization organization);
}
