package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.dao.JPAEmployeeDao;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.modelfactory.JPAEmployeeFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.EmployeeProxy;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JPAPayrollDatabase implements PayrollDatabase {

	private JPAEmployeeDao jPAEmployeeDao;

	@Inject
	public JPAPayrollDatabase(JPAEmployeeDao jPAEmployeeDao) {
		this.jPAEmployeeDao = jPAEmployeeDao;
	}

	@Override
	public EntityTransaction createTransaction() {
		return jPAEmployeeDao.createTransaction();
	}
	
	@Override
	public void addEmployee(Employee employee) {
		jPAEmployeeDao.persist(toJPAModel(employee));
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

	private static JPAEmployee toJPAModel(Employee employee) {
		return JPAEmployeeFactory.create(employee);
	}

	private static Employee toJPAProxiedDomainOrNull(JPAEmployee jpaEmployee) {
		return jpaEmployee == null ? null : new EmployeeProxy(jpaEmployee);
	}

}
