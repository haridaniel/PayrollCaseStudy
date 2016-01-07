package hu.daniel.hari.testthis.data.jpa.dao;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;
import hu.daniel.hari.testthis.data.jpa.model.paymentclassification.commissioned.JPASalesReceipt;

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
