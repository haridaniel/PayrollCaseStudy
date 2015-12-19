package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.inmemory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityTransaction;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.UnionMemberAffiliation;

public class InMemoryEntityGateway implements EmployeeGateway {

	private Map<Integer, Employee> employeesById = new HashMap<>();
	
//	@Override
//	public EntityFactory factory() {
//		return new InMemoryEntityFactory();
//	}
	
	@Override
	public void addEmployee(Employee employee) {
		employeesById.put(employee.getId(), employee);
	}

	@Override
	public Employee getEmployee(int employeeId) {
		Employee employee = employeesById.get(employeeId);
		assertNotNull(employee);
		return employee;
	}

	private void assertNotNull(Employee employee) {
		if(employee == null)
			throw new NoSuchEmployeeException();
	}

	@Override
	public Collection<Employee> getAllEmployees() {
		return new ArrayList<>(employeesById.values());
	}
	

	@Override
	public void deleteEmployee(int employeeId) {
		employeesById.remove(employeeId);
	}

	public EntityTransaction getTransaction() {
		return new DummyTransaction();
	}

	// Wrong performance, but now better than introduce complexity to business rules.
	@Override
	public int getEmployeeIdByUnionMemberId(int unionMemberId) {
		return employeesById.values().stream()
				.filter(employee -> (employee.getAffiliation() instanceof UnionMemberAffiliation))
				.filter(employee -> ((UnionMemberAffiliation) employee.getAffiliation()).getUnionMemberId() == unionMemberId)
				.findFirst()
				.orElseThrow(() -> new NoEmployeeWithSuchUnionMemberIdException())
				.getId();
	}

	@Override
	public void deleteAllEmployees() {
		employeesById.clear();
	}

}
