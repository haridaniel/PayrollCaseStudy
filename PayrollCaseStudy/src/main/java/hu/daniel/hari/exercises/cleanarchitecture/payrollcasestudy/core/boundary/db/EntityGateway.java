package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db;

import java.util.Collection;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;

public interface EntityGateway {
	
//	EntityFactory factory();
	
	/**
	 * @throws NoSuchEmployeeException
	 */
	Employee getEmployee(int employeeId);
	
	void addEmployee(Employee employee);
	
	Collection<Employee> getAllEmployees();
	
	void deleteEmployee(int employeeId);
	
	void deleteAllEmployees();
	
	/**
	 * @throws NoEmployeeWithSuchUnionMemberIdException
	 */
	int getEmployeeIdByUnionMemberId(int unionMemberId);
	
	
	public class NoSuchEmployeeException extends RuntimeException {}
	public static class NoEmployeeWithSuchUnionMemberIdException extends RuntimeException {}

}
