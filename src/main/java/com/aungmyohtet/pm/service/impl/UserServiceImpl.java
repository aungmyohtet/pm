package com.aungmyohtet.pm.service.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.dto.UserDto;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.Role;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.entity.VerificationToken;
import com.aungmyohtet.pm.repository.OrganizationRepository;
import com.aungmyohtet.pm.repository.ProjectRepository;
import com.aungmyohtet.pm.repository.RoleRepository;
import com.aungmyohtet.pm.repository.UserRepository;
import com.aungmyohtet.pm.repository.VerificationTokenRepository;
import com.aungmyohtet.pm.service.UserService;
import com.aungmyohtet.pm.web.error.InvalidTokenException;
import com.aungmyohtet.pm.web.error.UserNotExistException;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    ModelMapper modelMapper;

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User findById(long id) {
        return null;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return null;
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void verifyUser(String token) throws InvalidTokenException {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            throw new InvalidTokenException();
        }
        verificationToken.updateToken(UUID.randomUUID().toString());
        // update?
        User user = verificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void createUserAndVerificationToken(User user, String token) {
        Role role = roleRepository.getDefaultRole();
        user.setRole(role);
        userRepository.save(user);
        VerificationToken verificationToken = new VerificationToken(token, user);
        tokenRepository.save(verificationToken);
    }

    @Override
    @Transactional
    public void resetPassword(String token, String password) throws InvalidTokenException {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken != null) {
            User user = verificationToken.getUser();
            user.setPassword(password);
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void requestPasswordReset(String email, String token) throws UserNotExistException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotExistException();
        }
        VerificationToken verificationToken = new VerificationToken(token, user);
        tokenRepository.save(verificationToken);
    }

    @Override
    public UserDto converToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> findOrganizationsByUser(String email) {
        return userRepository.findOrganizationsByUser(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> findOrganizationsCreatedByUser(String email) {
        return userRepository.findOrganizationsCreatedByUser(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> findOrganizationsInvolvingUser(String email) {
        return userRepository.findOrganizationsInvolvingUser(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findMembersOfOrganization(int id) {
        return userRepository.findMembersOfOrganization(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findMembersOfProject(int organizationId, String projectName) {
        Project project = projectRepository.findByOrganizationIdAndProjectName(organizationId, projectName);
        return userRepository.findMembersOfPoject(project);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findMembersOfOrganization(String name) {
        return userRepository.findMembersOfOrganization(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findMembersOfProject(String organizationName, String projectName) {
        Project project = projectRepository.findByOrganizationNameAndProjectName(organizationName, projectName);
        return userRepository.findMembersOfPoject(project);
    } 

}
