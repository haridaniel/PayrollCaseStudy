package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase.usecases.find;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employee.list.EmployeeListUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.EmployeeListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.EmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.EmployeeListResponse.EmployeeForEmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.testutil.TestUtils;

public class EmployeeListUseCaseITTest extends AbstractFindEmployeesUseCaseITTest<EmployeeListResponse> {
	public EmployeeListUseCaseITTest(TestDatabaseProvider testDatabaseProvider) {
		super(testDatabaseProvider);
	}

	@Override
	protected EmployeeListResponse whenExecuteUseCase() {
		return useCaseFactories.employeeListUseCase().execute(new EmployeeListRequest(LocalDate.now()));
	}

	@Override
	protected EmployeeForEmployeeListResponse getSingleResultEmployeeItem(EmployeeListResponse response) {
		return TestUtils.singleResult(response.employees);
	}

}
