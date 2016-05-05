package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import com.google.inject.Provider;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.JPAEmployee;

public class JPAEmployeeDao {
	
	@Inject private Provider<EntityManager> entityManagerProvider;

	public void persist(JPAEmployee jpaEmployee) {
		entityManager().persist(jpaEmployee);
	}

	public JPAEmployee find(int employeeId) {
		return entityManager().find(JPAEmployee.class, employeeId);
	}
	
	public boolean isExists(int employeeId) {
		return find(employeeId) != null;
	}
	
	public List<JPAEmployee> findAll() {
		CriteriaQuery<JPAEmployee> criteria = entityManager().getCriteriaBuilder().createQuery(JPAEmployee.class);
		return entityManager().createQuery(
				criteria.select(criteria
						.from(JPAEmployee.class))
				).getResultList();
	}

	public void delete(int employeeId) {
		entityManager().remove(find(employeeId));
	}

	public void deleteAll() {
		for (JPAEmployee jpaEmployee : findAll()) {
			entityManager().remove(jpaEmployee);
		}
	}

	public boolean isEmployeeExistsByUnionMemberId(int unionMemberId) {
		return entityManager().createQuery(
				  "SELECT "
				+ "CASE WHEN COUNT(*) > 0 THEN true ELSE false END "
				+ "FROM JPAUnionMemberAffiliation unionMemberAffiliation "
				+ "WHERE unionMemberAffiliation.unionMemberId = :unionMemberId ",
						Boolean.class)
				.setParameter("unionMemberId", unionMemberId)
				.getSingleResult();
	}

	public int getEmployeeIdByUnionMemberId(int unionMemberId) {
		return entityManager().createQuery(
				  "SELECT employeeId "
				+ "FROM JPAUnionMemberAffiliation unionMemberAffiliation "
				+ "WHERE unionMemberAffiliation.unionMemberId = :unionMemberId ",
						Integer.class)
				.setParameter("unionMemberId", unionMemberId)
				.getSingleResult();
	}

	private EntityManager entityManager() {
		return entityManagerProvider.get();
	}

}
