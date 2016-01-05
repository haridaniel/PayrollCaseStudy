package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.fulfiller;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.Employee;

public interface PaymentFulFiller {

	void fulfillPayment(Employee employee, int amount);
	
}
