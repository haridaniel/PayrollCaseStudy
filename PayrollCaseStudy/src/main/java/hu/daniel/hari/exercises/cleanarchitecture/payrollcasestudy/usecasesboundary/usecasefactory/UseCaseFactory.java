package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.usecasefactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.AddSalesReceiptUseCase.AddSalesReceiptUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.AddServiceChargeUseCase.AddServiceChargeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.AddTimeCardUseCase.AddTimeCardUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.DeleteEmployeeUseCase.DeleteEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.PaydayUseCase.PaydayUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.changeaffiliation.AddUnionMemberAffiliationUseCase.AddUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.changeaffiliation.RemoveUnionMemberAffiliationUseCase.RemoveUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.changeemployee.ChangeEmployeeUseCase.ChangeEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.find.GetEmployeeUseCase.GetEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.find.ListEmployeesUseCase.ListEmployeesUseCaseFactory;

public interface UseCaseFactory extends 
		AddEmployeeUseCaseFactory,
		AddUnionMemberAffiliationUseCaseFactory,
		RemoveUnionMemberAffiliationUseCaseFactory,
		ChangeEmployeeUseCaseFactory,
		AddSalesReceiptUseCaseFactory,
		AddServiceChargeUseCaseFactory,
		AddTimeCardUseCaseFactory,
		DeleteEmployeeUseCaseFactory,
		PaydayUseCaseFactory,
		ListEmployeesUseCaseFactory,
		GetEmployeeUseCaseFactory
{
}
