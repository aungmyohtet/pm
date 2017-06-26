package com.aungmyohtet.pm.repository;

import com.aungmyohtet.pm.entity.Role;

public interface RoleRepository {

    void save(Role role);
    
    Role findById(int id);
    
    Role findByName(String name);
    
    Role getDefaultRole();

    Role getAdminRole();
}
