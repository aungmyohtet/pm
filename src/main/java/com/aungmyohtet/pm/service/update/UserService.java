package com.aungmyohtet.pm.service.update;

import java.util.List;

import com.aungmyohtet.pm.dto.UserDto;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Role;
import com.aungmyohtet.pm.entity.User;

public interface UserService {

    // wrap normal repository methods

    void save(User user);

    void delete(User user);

    List<User> findAll();

    User findByEmail(String email);

    List<User> findByOrganization(Organization organization);

    List<User> findByProject(Project project);

    User findById(int id);

    List<User> filterByEmailContainingText(String text);

    List<User> findByOrganizationAndRole(Organization organization, Role role);

    List<User> findByProjectAndRole(Project project, Role role);

    UserDto convertToDto(User user);
}
