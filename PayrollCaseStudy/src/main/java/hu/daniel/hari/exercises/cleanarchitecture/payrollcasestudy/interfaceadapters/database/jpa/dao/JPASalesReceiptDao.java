package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.dao;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.model.paymentclassification.commissioned.JPASalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.DateInterval;

public class JPASalesReceiptDao {
	
	private EntityManager entityManager;
	
	@Inject
	public JPASalesReceiptDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Collection<JPASalesReceipt> findJPASalesReceiptsIn(DateInterval dateInterval) {
		return entityManager.createQuery(
				"SELECT salesReceipt FROM JPASalesReceipt salesReceipt "
						+ "WHERE salesReceipt.id.date >= :intervalFrom "
						+ "AND	 salesReceipt.id.date <= :intervalTo ",
						JPASalesReceipt.class)
				.setParameter("intervalFrom", dateInterval.from)
				.setParameter("intervalTo", dateInterval.to)
				.getResultList();
	}
	
	
}
