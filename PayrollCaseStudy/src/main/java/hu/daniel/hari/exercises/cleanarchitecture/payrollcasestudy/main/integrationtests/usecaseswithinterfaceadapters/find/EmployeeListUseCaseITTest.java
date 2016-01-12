package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.find;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.EmployeeListUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.util.TestUtils;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.Request;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.EmployeeItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.EmployeeListResponse;

public class EmployeeListUseCaseITTest extends AbstractFindEmployeesUseCaseITTest<EmployeeListResponse> {
	public EmployeeListUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Override
	protected EmployeeListResponse whenExecuteUseCase() {
		EmployeeListUseCase employeesOverviewUseCase = useCaseFactory.employeeListUseCase();
		employeesOverviewUseCase.execute(Request.EMPTY_REQUEST);
		return employeesOverviewUseCase.getResponse();
	}

	@Override
	protected EmployeeItem getSingleResultEmployeeItem(EmployeeListResponse response) {
		return TestUtils.singleResult(response.employeeListItems);
	}

}
