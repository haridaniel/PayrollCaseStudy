package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.paymentclassification.PaymentClassificationResponseCreatorFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeItem.PaymentClassificationType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeListResponse.EmployeeListItem;

public class EmployeeListResponseCreator {
	//TODO: depend
	private PaymentClassificationResponseCreatorFactory paymentClassificationResponseCreatorFactory = new PaymentClassificationResponseCreatorFactory();
	
	private LocalDate currentDate;
	
	public EmployeeListResponseCreator(LocalDate currentDate) {
		this.currentDate = currentDate;
	}

	public EmployeeListResponse toResponse(Collection<Employee> employees) {
		return new EmployeeListResponse(
				employees.stream()
				.map(employee -> toEmployeeListItem(employee))
				.collect(Collectors.toList())
				);
	}

	private EmployeeListItem toEmployeeListItem(Employee employee) {
		return toEmployeeListItem(employee, paymentClassificationResponseCreatorFactory.create(employee.getPaymentClassification()));
	}

	private EmployeeListItem toEmployeeListItem(Employee employee, PaymentClassificationResponseCreator<PaymentClassification> paymentClassificationResponseCreator) {
		EmployeeListItem employeeListItem = new EmployeeListItem();
		employeeListItem.id = employee.getId();
		employeeListItem.name = employee.getName();
		employeeListItem.address = employee.getAddress();
		employeeListItem.paymentClassificationType = paymentClassificationResponseCreator.getType();
		employeeListItem.paymentClassificationTypeString = paymentClassificationResponseCreator.getFormattedType();
		employeeListItem.nextPayDay = employee.getPaymentSchedule().getSameOrNextPayDate(currentDate);
		return employeeListItem;
	}
	
	public static abstract class PaymentClassificationResponseCreator<T extends PaymentClassification> {
		protected T paymentClassification;
		
		public PaymentClassificationResponseCreator(T paymentClassification) {
			this.paymentClassification = paymentClassification;
		}
		public abstract PaymentClassificationType getType();
		public abstract String getFormattedType();
	}

	
}