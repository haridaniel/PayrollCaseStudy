package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.addemployee.AddCommissionedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.addemployee.AddEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.Employee.EmployeeFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.affiliation.Affiliation.AffiliationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.PaymentClassification.PaymentClassificationFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentmethod.PaymentMethod.PaymentMethodFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentschedule.PaymentSchedule.PaymentScheduleFactory;

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
