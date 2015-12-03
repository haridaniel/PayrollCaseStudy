package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db;

import java.util.Collection;

import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;

public interface PayrollDatabase {

	EntityTransaction createTransaction();
	void addEmployee(Employee employee);
	Employee getEmployee(int employeeId);
	Collection<Employee> getAllEmployees();
	void deleteAllEmployees();
	void deleteEmployee(int employeeId);

}