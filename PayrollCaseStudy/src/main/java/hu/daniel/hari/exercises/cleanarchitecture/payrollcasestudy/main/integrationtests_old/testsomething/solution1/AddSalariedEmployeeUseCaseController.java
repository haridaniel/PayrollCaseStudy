package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests_old.testsomething.solution1;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddSalariedEmployeeRequest;

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
