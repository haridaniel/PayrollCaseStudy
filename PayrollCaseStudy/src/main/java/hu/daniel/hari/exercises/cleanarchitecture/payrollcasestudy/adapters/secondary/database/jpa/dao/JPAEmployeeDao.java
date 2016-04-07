package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.commissioned.JPASalesReceipt;

public class JPAEmployeeDao {

	private EntityManager em;

	@Inject
	public JPAEmployeeDao(EntityManager em) {
		this.em = em;
	}

	public void persist(JPAEmployee jpaEmployee) {
		em.persist(jpaEmployee);
	}

	public JPAEmployee find(int employeeId) {
		return em.find(JPAEmployee.class, employeeId);
	}
	
	public boolean isExists(int employeeId) {
		return find(employeeId) != null;
	}
	
	public List<JPAEmployee> findAll() {
		CriteriaQuery<JPAEmployee> criteria = em.getCriteriaBuilder().createQuery(JPAEmployee.class);
		return em.createQuery(
				criteria.select(criteria
						.from(JPAEmployee.class))
				).getResultList();
	}

	public void delete(int employeeId) {
		em.remove(find(employeeId));
	}

	public void deleteAll() {
		for (JPAEmployee jpaEmployee : findAll()) {
			em.remove(jpaEmployee);
		}
	}

	public int getEmployeeIdByUnionMemberId(int unionMemberId) {
		return em.createQuery(
				  "SELECT employeeId "
				+ "FROM JPAUnionMemberAffiliation unionMemberAffiliation "
				+ "WHERE unionMemberAffiliation.unionMemberId = :unionMemberId ",
						Integer.class)
				.setParameter("unionMemberId", unionMemberId)
				.getSingleResult();
	}

}
