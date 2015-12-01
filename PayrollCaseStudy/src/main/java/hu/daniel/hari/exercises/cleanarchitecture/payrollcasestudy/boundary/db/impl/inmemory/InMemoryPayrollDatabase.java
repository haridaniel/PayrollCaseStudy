package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.impl.inmemory;

import java.util.HashMap;
import java.util.Map;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;

public class InMemoryPayrollDatabase implements PayrollDatabase {

	private Map<Integer, Employee> employeesById = new HashMap<>();

	@Override
	public void addEmployee(Employee employee) {
		employeesById.put(employee.id, employee);
	}

	@Override
	public Employee getEmployee(int employeeId) {
		return employeesById.get(employeeId);
	}

	@Override
	public void clearEmployees() {
		employeesById.clear();
	}

	@Override
	public void deleteEmployee(int employeeId) {
		employeesById.remove(employeeId);
	}

}
