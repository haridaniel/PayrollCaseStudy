package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.dao;

import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;

public class JPAEmployeeDao {

	private EntityManager em;

	@Inject
	public JPAEmployeeDao(EntityManager em) {
		this.em = em;
	}

	public EntityTransaction createTransaction() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		return transaction;
	}

	public void persist(JPAEmployee jpaEmployee) {
		em.persist(jpaEmployee);
	}

	public JPAEmployee find(int employeeId) {
		return em.find(JPAEmployee.class, employeeId);
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

}
