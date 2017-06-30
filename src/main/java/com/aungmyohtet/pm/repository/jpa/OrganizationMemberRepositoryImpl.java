package com.aungmyohtet.pm.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.OrganizationMember;
import com.aungmyohtet.pm.repository.OrganizationMemberRepository;

@Repository
public class OrganizationMemberRepositoryImpl implements OrganizationMemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(OrganizationMember organizationMember) {
        entityManager.persist(organizationMember);
    }

}
