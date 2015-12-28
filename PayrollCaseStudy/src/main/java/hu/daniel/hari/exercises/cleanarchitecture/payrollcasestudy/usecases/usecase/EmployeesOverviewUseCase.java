package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase;

import java.util.Collection;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.exception.NotImplementedException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.paymentclassification.SalariedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.Request.EmptyRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.EmployeesOverviewUseCaseResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.EmployeesOverviewUseCaseResponse.EmployeeItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.EmployeesOverviewUseCaseResponse.EmployeeItem.PaymentClassificationType;

public class EmployeesOverviewUseCase extends TransactionalEmployeeUseCase<EmptyRequest> {

	private EmployeesOverviewUseCaseResponse response;
	
	public EmployeesOverviewUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void executeInTransaction(EmptyRequest request) {
		response = toResponse(employeeGateway.findAll());
	}

	private EmployeesOverviewUseCaseResponse toResponse(Collection<Employee> employees) {
		EmployeesOverviewUseCaseResponse response = new EmployeesOverviewUseCaseResponse();
		response.employeeItems = employees.stream()
			.map(employee -> toEmployeeItem(employee))
			.collect(Collectors.toList());
		return response;
	}

	private EmployeeItem toEmployeeItem(Employee employee) {
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
			throw new NotImplementedException();
	}

	public EmployeesOverviewUseCaseResponse getResponse() {
		return response;
	}
	
	public static interface EmployeesOverviewUseCaseFactory {
		EmployeesOverviewUseCase employeesOverviewUseCase();
	}
	
}

