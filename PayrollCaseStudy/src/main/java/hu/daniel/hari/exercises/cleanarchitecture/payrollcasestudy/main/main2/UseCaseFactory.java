package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.main2;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddTimeCardUseCase.AddTimeCardUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;

public interface UseCaseFactory extends 
		AddEmployeeUseCaseFactory,
		AddTimeCardUseCaseFactory

{

}
