package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.addemployee.AddEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee.EmployeeFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.Affiliation.AffiliationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod.PaymentMethodFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule.PaymentScheduleFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddServiceChargeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.TransactionalUseCase;

public abstract class AddEmployeeUseCase<R extends AddEmployeeRequest> extends TransactionalUseCase<R> {
	private Employee employee;
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
	protected final void executeInTransaction(R request) {
		employee = employeeFactory.employee();
		
		setFields(request);
		setDefaultFields();
		setEmployeeTypeSpecificFields(request);
				
		employeeGateway.addEmployee(employee);
	}

	private void setFields(R request) {
		employee.setId(request.employeeId);
		employee.setName(request.name);
		employee.setAddress(request.address);
	}

	private void setDefaultFields() {
		employee.setPaymentMethod(paymentMethodFactory.holdPaymentMethod());
		employee.setAffiliation(affiliationFactory.noAffiliation());
	}

	private void setEmployeeTypeSpecificFields(R request) {
		employee.setPaymentClassification(getPaymentClassification(request));
		employee.setPaymentSchedule(getPaymentSchedule());
	}

	protected abstract PaymentClassification getPaymentClassification(R request);
	protected abstract PaymentSchedule getPaymentSchedule();

	public static interface AddEmployeeUseCaseFactory {
		AddSalariedEmployeeUseCase addSalariedEmployeeUseCase();
		AddHourlyEmployeeUseCase addHourlyEmployeeUseCase();
		AddCommissionedEmployeeUseCase addCommissionedEmployeeUseCase();
	}
	
}
