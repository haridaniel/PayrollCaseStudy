package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.dao.JPAEmployeeDao;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.EmployeeProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.ProxyFactory;

public class JPAPayrollDatabase implements PayrollDatabase {

	@Inject private JPAEntityFactory jpaEntityFactory;
	@Inject private JPAEmployeeDao jPAEmployeeDao;
	@Inject private TransactionProvider transactionProvider;
	@Inject private ProxyFactory proxyFactory;
	
	@Override
	public EntityFactory factory() {
		return jpaEntityFactory;
	}
	
	@Inject
	public JPAPayrollDatabase(JPAEmployeeDao jPAEmployeeDao, JPAEntityFactory jpaEntityFactory) {
		this.jPAEmployeeDao = jPAEmployeeDao;
		this.jpaEntityFactory = jpaEntityFactory;
	}

	@Override
	public EntityTransaction getTransaction() {
		return transactionProvider.getTransaction();
	}
	
	@Override
	public void addEmployee(Employee employee) {
		jPAEmployeeDao.persist(((EmployeeProxy) employee).getJPAObject());
	}

	@Override
	public Employee getEmployee(int employeeId) {
		JPAEmployee jpaEmployee = jPAEmployeeDao.find(employeeId);
		assertNotNull(jpaEmployee);
		return proxy(jpaEmployee);
	}

	private void assertNotNull(JPAEmployee jpaEmployee) {
		if(jpaEmployee == null)
			throw new NoSuchEmployeeException();
	}
	
	@Override
	public Collection<Employee> getAllEmployees() {
		return proxyAll(jPAEmployeeDao.findAll());
	}

	@Override
	public void deleteEmployee(int employeeId) {
		jPAEmployeeDao.delete(employeeId);
	}
	
	@Override
	public void clearDatabaseInTransaction() {
		executeInTransaction(() -> 
			jPAEmployeeDao.deleteAll());
	}

	private Collection<Employee> proxyAll(List<JPAEmployee> findAll) {
		return findAll
				.stream()
				.map(jPAEmployee -> proxy(jPAEmployee))
				.collect(Collectors.toList());
	}

	private Employee proxy(JPAEmployee jpaEmployee) {
		return proxyFactory.create(EmployeeProxy.class, jpaEmployee);
	}

	@Override
	public int getEmployeeIdByUnionMemberId(int unionMemberId) {
		try {
			return jPAEmployeeDao.getEmployeeIdByUnionMemberId(unionMemberId);
		} catch (NoResultException e) {
			throw new NoEmployeeWithSuchUnionMemberIdException();
		}
	}

}
