package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityTransaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.UnionMemberAffiliation;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;

public class InMemoryEntityGateway implements EmployeeGateway {

	private Map<Integer, Employee> employeesById = new HashMap<>();
	
	@Override
	public void addNew(Employee employee) {
		employeesById.put(employee.getId(), employee);
	}

	@Override
	public Employee findById(int employeeId) {
		Employee employee = employeesById.get(employeeId);
		assertNotNull(employee);
		return employee;
	}

	private void assertNotNull(Employee employee) {
		if(employee == null)
			throw new NoSuchEmployeeException();
	}

	@Override
	public Collection<Employee> findAll() {
		return new ArrayList<>(employeesById.values());
	}
	

	@Override
	public void deleteById(int employeeId) {
		employeesById.remove(employeeId);
	}

	// Wrong performance, but ok now
	@Override
	public int findByUnionMemberId(int unionMemberId) {
		return employeesById.values().stream()
				.filter(employee -> (employee.getAffiliation() instanceof UnionMemberAffiliation))
				.filter(employee -> ((UnionMemberAffiliation) employee.getAffiliation()).getUnionMemberId() == unionMemberId)
				.findFirst()
				.orElseThrow(() -> new NoSuchEmployeeException())
				.getId();
	}

	@Override
	public void deleteAll() {
		employeesById.clear();
	}

}
