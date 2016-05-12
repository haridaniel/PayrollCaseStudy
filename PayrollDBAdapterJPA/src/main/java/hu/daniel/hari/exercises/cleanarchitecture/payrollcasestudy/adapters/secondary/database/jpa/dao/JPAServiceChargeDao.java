package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.dao;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.affiliation.unionmember.JPAServiceCharge;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;

public class JPAServiceChargeDao {
	
	@Inject private EntityManager entityManager;
	
	public Collection<JPAServiceCharge> findBy(int unionMemberId, DateInterval dateInterval) {
		return entityManager.createQuery(
				  "SELECT sc FROM JPAServiceCharge sc "
				+ "WHERE sc.id.unionMemberId = :unionMemberId "
				+ "AND	 sc.id.date >= :intervalFrom "
				+ "AND	 sc.id.date <= :intervalTo ",
				JPAServiceCharge.class)
				.setParameter("unionMemberId", unionMemberId)
				.setParameter("intervalFrom", dateInterval.from)
				.setParameter("intervalTo", dateInterval.to)
				.getResultList();
	}
	
	
}
