package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.dao;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.hourly.JPATimeCard;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class TimeCardDao {
	
	private EntityManager entityManager;
	
	@Inject
	public TimeCardDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Collection<JPATimeCard> findTimeCardsIn(DateInterval dateInterval) {
		return entityManager.createQuery(
				  "SELECT tc FROM JPATimeCard tc "
				+ "WHERE tc.id.date >= :intervalFrom "
				+ "AND	 tc.id.date <= :intervalTo ",
				JPATimeCard.class)
				.setParameter("intervalFrom", dateInterval.from)
				.setParameter("intervalTo", dateInterval.to)
				.getResultList();
	}
	
	
}
