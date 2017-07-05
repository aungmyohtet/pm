package com.aungmyohtet.pm.repository.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aungmyohtet.pm.entity.Event;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.repository.EventRepository;

@Repository
public class EventRepositoryImpl implements EventRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Event> findEventsOfOrganization(String organizationName) {
		Query query = entityManager
				.createQuery("SELECT e FROM Event e " + "JOIN FETCH e.organization o " + "WHERE o.name=?", Event.class);
		query.setParameter(1, organizationName);
		// query.setParameter(2, "DEVELOPER");
		return query.getResultList();
	}

	@Override
	public Organization findByName(String organizationName) {
		Query query = entityManager.createQuery("SELECT o FROM Organization o WHERE o.name=?");
		query.setParameter(1, organizationName);
		Organization organization = (Organization) query.getSingleResult();
		return organization;
	}

	@Override
	public void add(Organization organization) {
		entityManager.persist(organization);
	}

}
