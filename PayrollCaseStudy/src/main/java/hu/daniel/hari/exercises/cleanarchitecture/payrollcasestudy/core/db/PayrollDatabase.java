package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.db;

import java.util.HashMap;
import java.util.Map;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;

public class PayrollDatabase {

	private static PayrollDatabase INSTANCE;

	public static PayrollDatabase get() {
		if (INSTANCE == null)
			INSTANCE = new PayrollDatabase();
		return INSTANCE;
	}

	private PayrollDatabase() {
	}

	private Map<Integer, Employee> employeesById = new HashMap<>();

	public void addEmployee(Employee employee) {
		employeesById.put(employee.id, employee);
	}

	public Employee getEmployee(int employeeId) {
		return employeesById.get(employeeId);
	}

	public void clearEmployees() {
		employeesById.clear();
	}

	public void deleteEmployee(int employeeId) {
		employeesById.remove(employeeId);
	}

}
