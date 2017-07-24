package com.aungmyohtet.pm.service.update.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Role;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.repository.update.UserRepository;
import com.aungmyohtet.pm.service.update.UserService;

@Service
public class UserServiceImplUpdate implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(User user) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public List<User> findByOrganization(Organization organization) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findByProject(Project project) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> filterByEmailContainingText(String text) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findByOrganizationAndRole(Organization organization, Role role) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findByProjectAndRole(Project project, Role role) {
        // TODO Auto-generated method stub
        return null;
    }

}
