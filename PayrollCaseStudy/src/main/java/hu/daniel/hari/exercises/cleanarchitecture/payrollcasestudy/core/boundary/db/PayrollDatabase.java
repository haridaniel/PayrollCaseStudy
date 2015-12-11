package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db;

import java.util.Collection;

import javax.persistence.EntityTransaction;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;

public interface PayrollDatabase {
	
	EntityFactory factory();

	EntityTransaction getTransaction();
	
	void addEmployee(Employee employee);
	Employee getEmployee(int employeeId);
	Collection<Employee> getAllEmployees();
	void deleteEmployee(int employeeId);
	void clearDatabase();
	int getEmployeeIdByUnionMemberId(int unionMemberId);
	
	public static class NoEmployeeWithSuchUnionMemberIdException extends RuntimeException {
	}

}