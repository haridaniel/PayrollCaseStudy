package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases;

import java.util.ArrayList;
import java.util.Collection;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.TransactionalUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.fulfiller.PaymentFulFiller;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.fulfiller.PaymentFulFillerSelector;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.PaydayRequest;

public class PaydayUseCase extends TransactionalUseCase<PaydayRequest> {
	
	private Collection<PayCheck> payChecks = new ArrayList<>();

	public PaydayUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway
			) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void executeInTransaction(PaydayRequest request) {
		Collection<Employee> employees = employeeGateway.findAll();
		
		for (Employee employee : employees) {
			if(employee.isPayDate(request.payDate)) {
				payChecks.add(employee.createPayCheck(request.payDate));
			}
		}
	}
	
	public Collection<PayCheck> getPayChecks() {
		return payChecks;
	}

	public static interface PaydayUseCaseFactory {
		PaydayUseCase paydayUseCase();
	}

}
