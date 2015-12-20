package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.main2;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddSalesReceiptUseCase.AddSalesReceiptUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddServiceChargeUseCase.AddServiceChargeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddTimeCardUseCase.AddTimeCardUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.DeleteEmployeeUseCase.DeleteEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.PaydayUseCase.PaydayUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeaffiliation.AddUnionMemberAffiliationUseCase.AddUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeaffiliation.RemoveUnionMemberAffiliationUseCase.RemoveUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee.ChangeEmployeeUseCase.ChangeEmployeeUseCaseFactory;

public interface UseCaseFactory extends 
		AddEmployeeUseCaseFactory,
		AddUnionMemberAffiliationUseCaseFactory,
		RemoveUnionMemberAffiliationUseCaseFactory,
		ChangeEmployeeUseCaseFactory,
		AddSalesReceiptUseCaseFactory,
		AddServiceChargeUseCaseFactory,
		AddTimeCardUseCaseFactory,
		DeleteEmployeeUseCaseFactory,
		PaydayUseCaseFactory
{
}
