package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee;

import java.util.ArrayList;
import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee.EmployeeFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.Affiliation.AffiliationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymentMethod.PaymentMethodFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalEmployeeGatewayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation.IdAlreadyExistsValidationError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation.UseCaseValidationException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation.ValidationError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public abstract class AddEmployeeUseCase<R extends AddEmployeeRequest> extends TransactionalEmployeeGatewayUseCase<R> {
	private EmployeeFactory employeeFactory;
	private PaymentMethodFactory paymentMethodFactory;
	private AffiliationFactory affiliationFactory;
	
	public AddEmployeeUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway, 
			EmployeeFactory employeeFactory, 
			PaymentMethodFactory paymentMethodFactory, 
			AffiliationFactory affiliationFactory
			) {
		super(transactionalRunner, employeeGateway);
		this.employeeFactory = employeeFactory;
		this.paymentMethodFactory = paymentMethodFactory;
		this.affiliationFactory = affiliationFactory;
	}
	
	@Override
	protected final void executeInTransaction(R request) throws UseCaseValidationException {
		new Validator().validate(request);
		
		Employee employee = employeeFactory.employee();
		
		setFields(employee, request);
		setDefaultFields(employee);
		setEmployeeTypeSpecificFields(employee, request);
				
		employeeGateway.addNew(employee);
	}


	private void setFields(Employee employee, R request) {
		employee.setId(request.employeeId);
		employee.setName(request.name);
		employee.setAddress(request.address);
	}

	private void setDefaultFields(Employee employee) {
		employee.setPaymentMethod(paymentMethodFactory.paymasterPaymentMethod());
		employee.setAffiliation(affiliationFactory.noAffiliation());
	}

	private void setEmployeeTypeSpecificFields(Employee employee, R request) {
		employee.setPaymentType(getPaymentType(request));
		employee.setPaymentSchedule(getPaymentSchedule());
	}

	protected abstract PaymentType getPaymentType(R request);
	protected abstract PaymentSchedule getPaymentSchedule();

	public static interface AddEmployeeUseCaseFactory {
		AddSalariedEmployeeUseCase addSalariedEmployeeUseCase();
		AddHourlyEmployeeUseCase addHourlyEmployeeUseCase();
		AddCommissionedEmployeeUseCase addCommissionedEmployeeUseCase();
	}
	
	private class Validator {
		List<ValidationError> validationErrors = new ArrayList<>();
		
		private void validate(R request) {
			checkIdExists(request);
			checkNameExists(request);
			throwIfThereAreErrors();
		}

		private void throwIfThereAreErrors() {
			if(!validationErrors.isEmpty())
				throw new UseCaseValidationException(validationErrors);
		}

		private void checkIdExists(R request) {
			if(employeeGateway.isExists(request.employeeId)) {
				Employee employee = employeeGateway.findById(request.employeeId);
				validationErrors.add(new IdAlreadyExistsValidationError(employee.getName()));
			}
		}
		
		private void checkNameExists(R request) {
			// TODO Auto-generated method stub
		}
		
	}
	
}
