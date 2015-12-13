package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;

public interface PayrollDatabase {
	
	EntityFactory factory();

	EntityTransaction getTransaction();
	
	void addEmployee(Employee employee);
	
	/**
	 * @throws NoSuchEmployeeException
	 */
	Employee getEmployee(int employeeId);
	Collection<Employee> getAllEmployees();
	void deleteEmployee(int employeeId);
	void clearDatabaseInTransaction();
	
	/**
	 * @throws NoEmployeeWithSuchUnionMemberIdException
	 */
	int getEmployeeIdByUnionMemberId(int unionMemberId);
	
	public class NoSuchEmployeeException extends RuntimeException {}
	public static class NoEmployeeWithSuchUnionMemberIdException extends RuntimeException {}
	
	public default void executeInTransaction(Runnable runnable) {
		EntityTransaction transaction = getTransaction();
		try {
			runnable.run();
			transaction.commit();
		} catch (RuntimeException e) {
			if(transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		}
	}

}