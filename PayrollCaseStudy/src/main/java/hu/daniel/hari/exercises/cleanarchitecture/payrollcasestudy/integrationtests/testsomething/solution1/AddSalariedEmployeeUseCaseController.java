package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.integrationtests.testsomething.solution1;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee.AddSalariedEmployeeUseCase;

public class AddSalariedEmployeeUseCaseController {
	
	private AddSalariedEmployeeUseCaseFactory addSalariedEmployeeUseCaseFactory;

	public AddSalariedEmployeeUseCaseController(AddSalariedEmployeeUseCaseFactory addSalariedEmployeeUseCaseFactory, UseCaseFactory useCaseFactory) {
		this.addSalariedEmployeeUseCaseFactory = addSalariedEmployeeUseCaseFactory;
		
		useCaseFactory.create(AddSalariedEmployeeUseCase.class);
		
	}
	
	public void doIt(int employeeId, String name, String address, int monthlySalary) {
		addSalariedEmployeeUseCaseFactory.create()
			.execute(new AddSalariedEmployeeRequest(employeeId, name, address, monthlySalary));
	}
	
}
