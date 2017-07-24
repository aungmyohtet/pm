package com.aungmyohtet.pm.service.update;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Role;
import com.aungmyohtet.pm.entity.User;

public interface RoleService {

    // wrap normal repository methods
    void save(Role role);

    Role findById(int id);

    Role findByName(String name);

    Role getDefaultRole();

    Role getAdminRole();

    Role findByUserAndOrganization(User user, Organization organization);

    Role findByUserAndProject(User user, Project project);
}
