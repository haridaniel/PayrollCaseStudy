package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.Employee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.CommissionedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.HourlyPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalariedPaymentType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.HasResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.TransactionalEmployeeGatewayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.GetEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.GetEmployeeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeItem.PaymentTypeEnum;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class GetEmployeeUseCase extends TransactionalEmployeeGatewayUseCase<GetEmployeeRequest> implements HasResponse<GetEmployeeResponse> {

	private EmployeeItemResponseCreator employeeItemResponseCreator = new EmployeeItemResponseCreator();
	private GetEmployeeResponse response;
	
	public GetEmployeeUseCase(TransactionalRunner transactionalRunner, EmployeeGateway employeeGateway) {
		super(transactionalRunner, employeeGateway);
	}

	@Override
	protected void executeInTransaction(GetEmployeeRequest request) {
		response = toResponse(employeeGateway.findById(request.employeeId));
	}

	private GetEmployeeResponse toResponse(Employee employee) {
		return new GetEmployeeResponse(employeeItemResponseCreator.toEmployeeItem(employee));
	}

	@Override
	public GetEmployeeResponse getResponse() {
		return response;
	}
	
	public static interface GetEmployeeUseCaseFactory {
		GetEmployeeUseCase getEmployeeUseCase();
	}
	
	private static class EmployeeItemResponseCreator {
		public EmployeeItem toEmployeeItem(Employee employee) {
			EmployeeItem employeeItem = new EmployeeItem();
			employeeItem.id = employee.getId();
			employeeItem.name = employee.getName();
			employeeItem.address = employee.getAddress();
			employeeItem.paymentTypeEnum = toPaymentTypeEnum(employee.getPaymentType());
			return employeeItem;
		}
		
		//TODO: duplicated
		private PaymentTypeEnum toPaymentTypeEnum(PaymentType paymentType) {
			if (paymentType instanceof SalariedPaymentType)
				return PaymentTypeEnum.SALARIED;
			else if (paymentType instanceof HourlyPaymentType)
				return PaymentTypeEnum.HOURLY;
			else if (paymentType instanceof CommissionedPaymentType)
				return PaymentTypeEnum.COMMISSIONED;
			else
				throw new IllegalArgumentException();
		}
	}

}

