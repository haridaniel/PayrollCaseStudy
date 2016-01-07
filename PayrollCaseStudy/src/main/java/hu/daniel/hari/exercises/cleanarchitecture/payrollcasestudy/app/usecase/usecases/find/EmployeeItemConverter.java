package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeItem.PaymentClassificationType;

class EmployeeItemConverter {
	public EmployeeItem toEmployeeItem(Employee employee) {
		EmployeeItem employeeItem = new EmployeeItem();
		employeeItem.id = employee.getId();
		employeeItem.name = employee.getName();
		employeeItem.address = employee.getAddress();
		employeeItem.paymentClassificationType = toPaymentClassificationType(employee.getPaymentClassification());
		return employeeItem;
	}
	
	private PaymentClassificationType toPaymentClassificationType(PaymentClassification paymentClassification) {
		if (paymentClassification instanceof SalariedPaymentClassification)
			return PaymentClassificationType.SALARIED;
		else if (paymentClassification instanceof HourlyPaymentClassification)
			return PaymentClassificationType.HOURLY;
		else if (paymentClassification instanceof CommissionedPaymentClassification)
			return PaymentClassificationType.COMMISSIONED;
		else
			throw new IllegalArgumentException();
	}
}