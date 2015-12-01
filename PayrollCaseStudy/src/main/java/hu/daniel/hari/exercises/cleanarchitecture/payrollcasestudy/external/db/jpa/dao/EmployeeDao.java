package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.dao;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EmployeeDao {

	private EntityManager em;

	@Inject
	public EmployeeDao(EntityManager em) {
		this.em = em;
	}
	
	public void persist(JPAEmployee employee) {
		em.persist(employee);
	}
	
	public JPAEmployee find(int id) {
		return em.find(JPAEmployee.class, id);
	}

	public void deleteAll() {
		em.createQuery("DELETE FROM " + JPAEmployee.class.getSimpleName())
			.executeUpdate();
		em.clear(); //I don't know why is this needed
	}

	public void delete(int employeeId) {
		JPAEmployee jpaEmployee = em.find(JPAEmployee.class, employeeId);
		em.remove(jpaEmployee);
		
	}


}
