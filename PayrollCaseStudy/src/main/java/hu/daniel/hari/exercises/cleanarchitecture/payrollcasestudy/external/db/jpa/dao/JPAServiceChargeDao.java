package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.dao;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.affiliation.unionmember.JPAServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.hourly.JPATimeCard;

public class JPAServiceChargeDao {
	
	@Inject private EntityManager entityManager;
	
	public Collection<JPAServiceCharge> findJPAServiceChargesIn(DateInterval dateInterval) {
		return entityManager.createQuery(
				  "SELECT sc FROM JPAServiceCharge sc "
				+ "WHERE sc.id.date >= :intervalFrom "
				+ "AND	 sc.id.date <= :intervalTo ",
				JPAServiceCharge.class)
				.setParameter("intervalFrom", dateInterval.from)
				.setParameter("intervalTo", dateInterval.to)
				.getResultList();
	}
	
	
}
