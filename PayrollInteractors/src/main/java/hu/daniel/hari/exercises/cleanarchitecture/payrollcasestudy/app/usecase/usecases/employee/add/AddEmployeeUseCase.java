package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employee.add;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee.EmployeeFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.Affiliation.AffiliationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymentMethod.PaymentMethodFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.PaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.exception.multiple.MultipleErrorsUseCaseExceptionThrower;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.EmployeeGatewayCommandUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.addemployee.AddEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.add.AddEmployeeError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.add.IdAlreadyExistsValidationError;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public abstract class AddEmployeeUseCase<R extends AddEmployeeRequest> extends EmployeeGatewayCommandUseCase<R> {
	private EmployeeFactory employeeFactory;
	private PaymentMethodFactory paymentMethodFactory;
	private AffiliationFactory affiliationFactory;
	private R request;
	
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
	protected final void executeInTransaction(R request){
		this.request = request;
		new Validator();

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

	private final class Validator extends MultipleErrorsUseCaseExceptionThrower<AddEmployeeError> {

		@Override
		protected void addErrors() {
			checkIdExists();
			checkNameExists();
		}
		
		private void checkIdExists() {
			if(employeeGateway.isExists(request.employeeId)) {
				Employee employee = employeeGateway.findById(request.employeeId);
				addError(new IdAlreadyExistsValidationError(employee.getName()));
			}
		}
		
		private void checkNameExists() {
			// TODO Auto-generated method stub
		}
		
	}
	
}
