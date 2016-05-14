package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.timecard;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.EmployeeGatewayCommandUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.timecard.AbstractTimeCardRequest;
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
			throw new NotHourlyEmployeeException();
		}
	}
	
	protected abstract void executeTimeCardOperation(R request, HourlyPaymentType hourlyPaymentType);
	
}
