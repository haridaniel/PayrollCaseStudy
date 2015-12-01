package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.google.inject.persist.Transactional;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.dao.EmployeeDao;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.JPAEmployee;

public class JPAPayrollDatabase implements PayrollDatabase {

	private EmployeeDao employeeDao;

	@Inject
	public JPAPayrollDatabase(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	@Transactional
	@Override
	public void addEmployee(Employee employee) {
		employeeDao.persist(toJPAEmployee(employee));
	}

	@Override
	public Employee getEmployee(int employeeId) {
		JPAEmployee jpaEmployee = employeeDao.find(employeeId);
		return jpaEmployee == null? null : toEmployee(jpaEmployee);
	}

	@Transactional
	@Override
	public void clearEmployees() {
		employeeDao.deleteAll();
	}

	@Transactional
	@Override
	public void deleteEmployee(int employeeId) {
		employeeDao.delete(employeeId);
	}

	private JPAEmployee toJPAEmployee(Employee employee) {
		JPAEmployee jpaEmployee = new JPAEmployee();
		jpaEmployee.id = employee.id;
		jpaEmployee.name = employee.getName();
		jpaEmployee.address = employee.address;
		return jpaEmployee;
	}

	private Employee toEmployee(JPAEmployee jpaEmployee) {
		Employee employee = new Employee();
		employee.id = jpaEmployee.id;
		employee.setName(jpaEmployee.name);
		employee.address = jpaEmployee.address;
		return employee;
	}
	
}
