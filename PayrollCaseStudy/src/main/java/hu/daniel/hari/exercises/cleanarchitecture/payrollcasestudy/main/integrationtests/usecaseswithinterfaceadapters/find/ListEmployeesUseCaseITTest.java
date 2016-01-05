package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.find;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.util.TestUtils;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.find.ListEmployeesUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.Request;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.Request.EmptyRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.EmployeeItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.EmployeeItem.PaymentClassificationType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.ListEmployeesUseCaseResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.Response;

public class ListEmployeesUseCaseITTest extends AbstractFindEmployeesUseCaseITTest<ListEmployeesUseCaseResponse> {
	public ListEmployeesUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Override
	protected ListEmployeesUseCaseResponse whenExecuteUseCase() {
		ListEmployeesUseCase employeesOverviewUseCase = useCaseFactory.listEmployeesUseCase();
		employeesOverviewUseCase.execute(Request.EMPTY_REQUEST);
		return employeesOverviewUseCase.getResponse();
	}

	@Override
	protected EmployeeItem getSingleResultEmployeeItem(ListEmployeesUseCaseResponse response) {
		return TestUtils.singleResult(response.employeeItems);
	}

}
