package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.addemployee.AddEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.TransactionalUseCase;

public abstract class AddEmployeeUseCase<R extends AddEmployeeRequest> extends TransactionalUseCase<R> {
	private Employee employee;
	
	public AddEmployeeUseCase(Database database) {
		super(database);
	}
	
	@Override
	protected final void executeInTransaction(R request) {
		employee = entityGateway.factory().employee();
		
		setFields(request);
		setDefaultFields();
		setEmployeeTypeSpecificFields(request);
				
		entityGateway.addEmployee(employee);
	}

	private void setFields(R request) {
		employee.setId(request.employeeId);
		employee.setName(request.name);
		employee.setAddress(request.address);
	}

	private void setDefaultFields() {
		employee.setPaymentMethod(entityGateway.factory().holdPaymentMethod());
		employee.setAffiliation(entityGateway.factory().noAffiliation());
	}

	private void setEmployeeTypeSpecificFields(R request) {
		employee.setPaymentClassification(getPaymentClassification(request));
		employee.setPaymentSchedule(getPaymentSchedule());
	}

	protected abstract PaymentClassification getPaymentClassification(R request);
	protected abstract PaymentSchedule getPaymentSchedule();

}
