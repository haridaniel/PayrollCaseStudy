package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.dao;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.commissioned.JPASalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;

public class JPASalesReceiptDao {
	
	private EntityManager entityManager;
	
	@Inject
	public JPASalesReceiptDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Collection<JPASalesReceipt> findBy(int employeeId, DateInterval dateInterval) {
		return entityManager.createQuery(
				"SELECT salesReceipt FROM JPASalesReceipt salesReceipt "
						+ "WHERE salesReceipt.id.employeeId = :employeeId "
						+ "AND	 salesReceipt.id.date >= :intervalFrom "
						+ "AND	 salesReceipt.id.date <= :intervalTo ",
						JPASalesReceipt.class)
				.setParameter("employeeId", employeeId)
				.setParameter("intervalFrom", dateInterval.from)
				.setParameter("intervalTo", dateInterval.to)
				.getResultList();
	}
	
	
}
