package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator.paymentclassification.PaymentTypeResponseCreatorFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.visitortest.PayClassFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeItem.PaymentTypeEnum;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeListResponse.EmployeeListItem;

public class EmployeeListResponseCreator {
	//TODO: depend
	private PaymentTypeResponseCreatorFactory paymentTypeResponseCreatorFactory = new PaymentTypeResponseCreatorFactory();
	private PayClassFormatter payClassFormatter = new PayClassFormatter();
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
		return toEmployeeListItem(employee, paymentTypeResponseCreatorFactory.create(employee.getPaymentType()));
	}

	private EmployeeListItem toEmployeeListItem(Employee employee, PaymentTypeResponseCreator<PaymentType> paymentTypeResponseCreator) {
		EmployeeListItem employeeListItem = new EmployeeListItem();
		employeeListItem.id = employee.getId();
		employeeListItem.name = employee.getName();
		employeeListItem.address = employee.getAddress();
		employeeListItem.paymentTypeEnum = paymentTypeResponseCreator.getType();
//		employeeListItem.paymentTypeTypeString = paymentTypeResponseCreator.getFormattedType();
		
		employeeListItem.paymentTypeString = employee.getPaymentType().accept(payClassFormatter);
		
		employeeListItem.nextPayDay = employee.getPaymentSchedule().getSameOrNextPayDate(currentDate);
		return employeeListItem;
	}
	
	public static abstract class PaymentTypeResponseCreator<T extends PaymentType> {
		protected T paymentType;
		
		public PaymentTypeResponseCreator(T paymentType) {
			this.paymentType = paymentType;
		}
		public abstract PaymentTypeEnum getType();
		public abstract String getFormattedType();
	}

	
}