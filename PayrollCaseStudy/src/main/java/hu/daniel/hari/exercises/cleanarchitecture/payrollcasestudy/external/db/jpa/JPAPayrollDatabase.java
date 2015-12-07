package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.dao.JPAEmployeeDao;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.EmployeeProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.EmployeeProxy.EmployeeProxyFactory;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JPAPayrollDatabase implements PayrollDatabase {

	private JPAEntityFactory jpaEntityFactory;
	private JPAEmployeeDao jPAEmployeeDao;
	
	@Inject
	private EntityManager em;
	@Inject
	private EmployeeProxyFactory employeeProxyFactory;
	
	@Override
	public EntityFactory create() {
		return jpaEntityFactory;
	}

	@Inject
	public JPAPayrollDatabase(JPAEmployeeDao jPAEmployeeDao, JPAEntityFactory jpaEntityFactory) {
		this.jPAEmployeeDao = jPAEmployeeDao;
		this.jpaEntityFactory = jpaEntityFactory;
	}

	@Override
	public EntityTransaction createTransaction() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		return transaction;
	}
	
	@Override
	public void addEmployee(Employee employee) {
		EmployeeProxy employeeProxy = (EmployeeProxy) employee;
		jPAEmployeeDao.persist(employeeProxy.getJpaEmployee());
	}

	@Override
	public Employee getEmployee(int employeeId) {
		return toJPAProxiedDomainOrNull(jPAEmployeeDao.find(employeeId));
	}
	
	@Override
	public Collection<Employee> getAllEmployees() {
		return jPAEmployeeDao.findAll()
				.stream()
				.map(jPAEmployee -> toJPAProxiedDomainOrNull(jPAEmployee))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteEmployee(int employeeId) {
		jPAEmployeeDao.delete(employeeId);
	}
	
	@Override
	public void deleteAllEmployees() {
		jPAEmployeeDao.deleteAll();
	}

	private Employee toJPAProxiedDomainOrNull(JPAEmployee jpaEmployee) {
		return jpaEmployee == null ? null : employeeProxyFactory.create(jpaEmployee);
	}

}
