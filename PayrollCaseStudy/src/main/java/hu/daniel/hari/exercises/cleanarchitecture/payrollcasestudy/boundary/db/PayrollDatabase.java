package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;

public interface PayrollDatabase {

	void addEmployee(Employee employee);
	Employee getEmployee(int employeeId);
	void clearEmployees();
	void deleteEmployee(int employeeId);

}