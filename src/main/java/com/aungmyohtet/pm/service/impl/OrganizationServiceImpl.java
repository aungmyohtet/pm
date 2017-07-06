package com.aungmyohtet.pm.service.impl;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aungmyohtet.pm.dto.OrganizationDto;
import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.OrganizationMember;
import com.aungmyohtet.pm.entity.Project;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.repository.OrganizationMemberRepository;
import com.aungmyohtet.pm.repository.OrganizationRepository;
import com.aungmyohtet.pm.repository.RoleRepository;
import com.aungmyohtet.pm.repository.UserRepository;
import com.aungmyohtet.pm.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrganizationMemberRepository organizationMemberRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    public void setOrganizationRepository(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void save(Organization organization) {
        organizationRepository.save(organization);
    }

    @Override
    @Transactional
    public void addByUser(Organization organization, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        organizationRepository.save(organization);
        OrganizationMember organizationMember = new OrganizationMember();
        organizationMember.setOrganization(organization);
        organizationMember.setUser(user);
        organizationMember.setRole(roleRepository.findByName("MANAGER"));
        organizationMemberRepository.add(organizationMember);
    }

    @Override
    @Transactional
    public Organization findByIdFetchingMembers(int id) {
        return organizationRepository.findByIdFetchingMembers(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationMember> findMembersByOrganization(int id) {
        return organizationRepository.findMembersByOrganization(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> findProjectsByOrganization(int id) {
        return organizationRepository.findProjectsByOrganization(id);
    }

    @Override
    public OrganizationDto convertToDto(Organization organization) {
        return modelMapper.map(organization, OrganizationDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> findProjectsByOrganization(String name) {
        return organizationRepository.findProjectsByOrganizationName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> findBoardsByOrganization(String organizationName) {
        return organizationRepository.findBoardsByOrganizationName(organizationName);
        
    }   

    public Organization findByName(String name) {
        return organizationRepository.findByName(name);
    }
}
