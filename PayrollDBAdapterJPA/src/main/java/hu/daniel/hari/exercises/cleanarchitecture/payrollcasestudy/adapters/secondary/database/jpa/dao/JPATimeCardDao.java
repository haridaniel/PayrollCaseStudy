package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.dao;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.hourly.JPATimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;

public class JPATimeCardDao {
	
	private EntityManager entityManager;
	
	@Inject
	public JPATimeCardDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Collection<JPATimeCard> findBy(int employeeId, DateInterval dateInterval) {
		return entityManager.createQuery(
				  "SELECT tc FROM JPATimeCard tc "
				+ "WHERE 	tc.id.employeeId = :employeeId "
				+ "AND		tc.id.date >= :intervalFrom "
				+ "AND	 	tc.id.date <= :intervalTo ",
				JPATimeCard.class)
				.setParameter("employeeId", employeeId)
				.setParameter("intervalFrom", dateInterval.from)
				.setParameter("intervalTo", dateInterval.to)
				.getResultList();
	}

	
	
}
