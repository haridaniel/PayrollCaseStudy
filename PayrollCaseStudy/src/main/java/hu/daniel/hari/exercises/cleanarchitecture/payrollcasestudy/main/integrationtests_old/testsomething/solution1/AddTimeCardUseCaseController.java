package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.integrationtests_old.testsomething.solution1;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.AddTimeCardRequest;

public class AddTimeCardUseCaseController {
	
	private AddTimeCardUseCaseFactory addTimeCardUseCaseFactory;

	public AddTimeCardUseCaseController(AddTimeCardUseCaseFactory addTimeCardUseCaseFactory) {
		this.addTimeCardUseCaseFactory = addTimeCardUseCaseFactory;
	}
	
	public void addTimeCard(int employeeId, LocalDate date, int workingHoursQty) {
		addTimeCardUseCaseFactory.create()
			.execute(new AddTimeCardRequest(employeeId, date, workingHoursQty));
		
	}
	
}
