package com.aungmyohtet.pm.repository.update.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.entity.OrganizationMember;
import com.aungmyohtet.pm.repository.update.OrganizationMemberRepository;

@Repository
public class OrganizationMembereRepositoryImplUpdate implements OrganizationMemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrganizationMember> findByOrganization(Organization organization) {
        Query query = this.entityManager.createQuery("SELECT m FROM OrganizationMember m WHERE m.organization = ?", OrganizationMember.class);
        query.setParameter(1, organization);
        return query.getResultList();
    }

}
