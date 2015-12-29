package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database;

import java.util.Collection;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;

public interface EmployeeGateway {
	
	/**
	 * @throws NoSuchEmployeeException
	 */
	Employee findById(int employeeId);
	/**
	 * @throws NoSuchEmployeeException
	 */
	int findByUnionMemberId(int unionMemberId);
	
	Collection<Employee> findAll();
	
	void addNew(Employee employee);
	
	void deleteById(int employeeId);
	
	void deleteAll();
	
	public static class NoSuchEmployeeException extends RuntimeException {}

}
