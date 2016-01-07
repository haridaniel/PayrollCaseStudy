package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee.EmployeeFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.affiliation.Affiliation.AffiliationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification.PaymentClassificationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentmethod.PaymentMethod.PaymentMethodFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentschedule.PaymentSchedule.PaymentScheduleFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddCommissionedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class AddCommissionedEmployeeUseCase extends AddEmployeeUseCase<AddCommissionedEmployeeRequest> {
	private PaymentClassificationFactory paymentClassificationFactory;
	private PaymentScheduleFactory paymentScheduleFactory;

	public AddCommissionedEmployeeUseCase(
			TransactionalRunner transactionalRunner, 
			EmployeeGateway employeeGateway, 
			EmployeeFactory employeeFactory, 
			PaymentMethodFactory paymentMethodFactory, 
			AffiliationFactory affiliationFactory, 
			PaymentClassificationFactory paymentClassificationFactory,
			PaymentScheduleFactory paymentScheduleFactory
			) {
		super(transactionalRunner, employeeGateway, employeeFactory, paymentMethodFactory, affiliationFactory);
		this.paymentClassificationFactory = paymentClassificationFactory;
		this.paymentScheduleFactory = paymentScheduleFactory;
	}

	@Override
	protected PaymentClassification getPaymentClassification(AddCommissionedEmployeeRequest request) {
		return paymentClassificationFactory.commissionedPaymentClassification(request.biWeeklyBaseSalary, request.commissionRate);
	}

	@Override
	protected PaymentSchedule getPaymentSchedule() {
		return paymentScheduleFactory.biWeeklyPaymentSchedule();
	}

}
