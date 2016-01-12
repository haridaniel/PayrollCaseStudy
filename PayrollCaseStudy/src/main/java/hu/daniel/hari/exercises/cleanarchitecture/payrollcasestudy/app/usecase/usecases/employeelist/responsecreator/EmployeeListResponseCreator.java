package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.responsecreator;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.CommissionedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalariedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType.PaymentTypeVisitor;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.EmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.EmployeeListResponse.EmployeeListItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.CommissionedPaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.HourlyPaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.PaymentTypeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.paymenttype.SalariedPaymentTypeResponse;

public class EmployeeListResponseCreator {
	private LocalDate currentDate;
	private PaymentTypeResponseFactory paymentTypeResponseFactory = new PaymentTypeResponseFactory();
	
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
		EmployeeListItem employeeListItem = new EmployeeListItem();
		employeeListItem.id = employee.getId();
		employeeListItem.name = employee.getName();
		employeeListItem.address = employee.getAddress();
		employeeListItem.paymentTypeResponse = employee.getPaymentType().accept(paymentTypeResponseFactory);
		employeeListItem.nextPayDay = employee.getPaymentSchedule().getSameOrNextPayDate(currentDate);
		return employeeListItem;
	}
	
	
	public static class PaymentTypeResponseFactory implements PaymentTypeVisitor<PaymentTypeResponse>{

		@Override
		public PaymentTypeResponse visit(CommissionedPaymentType commissionedPaymentType) {
			return new CommissionedPaymentTypeResponse(commissionedPaymentType.getBiWeeklyBaseSalary(), commissionedPaymentType.getCommissionRate());
		}

		@Override
		public PaymentTypeResponse visit(SalariedPaymentType salariedPaymentType) {
			return new SalariedPaymentTypeResponse(salariedPaymentType.getMonthlySalary());
		}

		@Override
		public PaymentTypeResponse visit(HourlyPaymentType hourlyPaymentType) {
			return new HourlyPaymentTypeResponse(hourlyPaymentType.getHourlyWage());
		}

	}
	
}