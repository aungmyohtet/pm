package com.aungmyohtet.pm.service;

import java.util.List;

import com.aungmyohtet.pm.dto.UserDto;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.web.error.InvalidTokenException;
import com.aungmyohtet.pm.web.error.UserNotExistException;

public interface UserService {

    void save(User user);

    User findById(long id);

    List<User> findAll();

    User findByEmail(String email);

    void verifyUser(String token) throws InvalidTokenException;

    void createUserAndVerificationToken(User user, String token);

    void requestPasswordReset(String email, String token) throws UserNotExistException;

    void resetPassword(String token, String password) throws InvalidTokenException;

    UserDto converToDto(User user);

    List<Organization> findOrganizationsByUser(String email);

    List<Organization> findOrganizationsCreatedByUser(String email);

    List<Organization> findOrganizationsInvolvingUser(String email);

    List<User> findMembersOfOrganization(int id);

    List<User> findMembersOfProject(int organizationId, String projectName);

    List<User> findMembersOfOrganization(String name);

    List<User> findMembersOfProject(String organizationName, String projectName);

    User findMembersOfProject(String organizationName, String projectName, String email);

    User findMembersOfOrganization(String name, String email);

}
