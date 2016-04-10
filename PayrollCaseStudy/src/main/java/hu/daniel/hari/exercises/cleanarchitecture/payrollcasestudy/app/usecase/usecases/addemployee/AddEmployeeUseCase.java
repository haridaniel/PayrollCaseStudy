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
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AddEmployeeValidationException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AddEmployeeValidationException.IdAlreadyExistsValidationError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AddEmployeeValidationException.RequiredFieldValidationError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AddEmployeeValidationException.AddEmployeeValidationError;
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
	protected final void executeInTransaction(R request) throws AddEmployeeValidationException {
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
		employee.setPaymentMethod(paymentMethodFactory.holdPaymentMethod());
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
		List<AddEmployeeValidationError> addEmployeeValidationErrors = new ArrayList<>();
		
		private void validate(R request) {
			checkRequiredFields(request);
			throwIfThereAreErrors();
			
			checkIdExists(request);
			checkNameExists(request);
			throwIfThereAreErrors();
		}

		private void throwIfThereAreErrors() {
			if(!addEmployeeValidationErrors.isEmpty())
				throw new AddEmployeeValidationException(addEmployeeValidationErrors);
		}

		private void checkRequiredFields(R request) {
			if(request.employeeId == null)
				addEmployeeValidationErrors.add(new RequiredFieldValidationError("id"));
		}

		private void checkIdExists(R request) {
			if(employeeGateway.isExists(request.employeeId)) {
				Employee employee = employeeGateway.findById(request.employeeId);
				addEmployeeValidationErrors.add(new IdAlreadyExistsValidationError(employee.getName()));
			}
		}
		
		private void checkNameExists(R request) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
