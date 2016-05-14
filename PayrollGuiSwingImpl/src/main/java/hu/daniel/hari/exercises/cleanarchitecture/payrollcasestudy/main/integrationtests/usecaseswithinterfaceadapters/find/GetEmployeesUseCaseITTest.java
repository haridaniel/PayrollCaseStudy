package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.usecaseswithinterfaceadapters.find;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests.config.DatabaseProvider;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.GetEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.GetEmployeeResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.GetEmployeeResponse.EmployeeForGetEmployeeResponse;

public class GetEmployeesUseCaseITTest extends AbstractFindEmployeesUseCaseITTest<GetEmployeeResponse> {
	public GetEmployeesUseCaseITTest(DatabaseProvider databaseProvider) {
		super(databaseProvider);
	}

	@Override
	protected GetEmployeeResponse whenExecuteUseCase() {
		return useCaseFactories.getEmployeeUseCase().execute(new GetEmployeeRequest(employeeId));
	}

	@Override
	protected EmployeeForGetEmployeeResponse getSingleResultEmployeeItem(GetEmployeeResponse response) {
		return response.employeeForGetEmployeeResponse;
	}


}
