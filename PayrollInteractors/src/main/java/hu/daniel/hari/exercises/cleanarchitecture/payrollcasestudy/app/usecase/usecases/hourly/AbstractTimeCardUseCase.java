package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.hourly;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.PaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.EmployeeGatewayCommandUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.exception.UseCaseException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.hourly.AbstractTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public abstract class AbstractTimeCardUseCase<R extends AbstractTimeCardRequest> extends EmployeeGatewayCommandUseCase<R> {

	public AbstractTimeCardUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway 
			) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void executeInTransaction(R request) {
		Employee employee = employeeGateway.findById(request.employeeId);
		HourlyPaymentType hourlyPaymentType = castHourlyPaymentType(employee.getPaymentType());
		executeTimeCardOperation(request, hourlyPaymentType);
	}

	private HourlyPaymentType castHourlyPaymentType(PaymentType paymentType) {
		if(paymentType instanceof HourlyPaymentType) {
			return (HourlyPaymentType) paymentType;
		} else {
			throw new NotHourlyPaymentTypeException();
		}
	}
	
	protected abstract void executeTimeCardOperation(R request, HourlyPaymentType hourlyPaymentType);
	
	public static class NotHourlyPaymentTypeException extends UseCaseException {
	}
	
}
