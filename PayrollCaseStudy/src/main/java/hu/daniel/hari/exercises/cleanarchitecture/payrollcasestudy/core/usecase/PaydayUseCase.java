package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase;

import java.util.ArrayList;
import java.util.Collection;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.PaydayRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.Database;

public class PaydayUseCase extends TransactionalUseCase<PaydayRequest> {
	private Collection<PayCheck> payChecks = new ArrayList<>();

	public PaydayUseCase(Database database) {
		super(database);
	}

	@Override
	protected void executeInTransaction(PaydayRequest request) {
		Collection<Employee> employees = entityGateway.getAllEmployees();
		
		for (Employee employee : employees) {
			if(employee.isPayDate(request.payDate)) {
				payChecks.add(employee.createPayCheck(request.payDate));
			}
		}
	}
	
	//TODO: Useless. Paycheck has no employeeId
	public Collection<PayCheck> getPayChecks() {
		return payChecks;
	}
	
}
