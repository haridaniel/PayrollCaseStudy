package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.usecasefactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.AddSalesReceiptUseCase.AddSalesReceiptUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.AddServiceChargeUseCase.AddServiceChargeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.AddTimeCardUseCase.AddTimeCardUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.DeleteEmployeeUseCase.DeleteEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.PaydayUseCase.PaydayUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.changeaffiliation.AddUnionMemberAffiliationUseCase.AddUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.changeaffiliation.RemoveUnionMemberAffiliationUseCase.RemoveUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.changeemployee.ChangeEmployeeUseCase.ChangeEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.changeemployee.paymentmethod.ChangeToAbstractPaymentMethodUseCase.ChangeToAbstractPaymentMethodUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.find.GetEmployeeUseCase.GetEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.find.ListEmployeesUseCase.ListEmployeesUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.sendpay.SendPayUseCase.SendPayUseCaseFactory;

public interface UseCaseFactory extends 
		AddEmployeeUseCaseFactory,
		ChangeToAbstractPaymentMethodUseCaseFactory,
		AddUnionMemberAffiliationUseCaseFactory,
		RemoveUnionMemberAffiliationUseCaseFactory,
		ChangeEmployeeUseCaseFactory,
		AddSalesReceiptUseCaseFactory,
		AddServiceChargeUseCaseFactory,
		AddTimeCardUseCaseFactory,
		DeleteEmployeeUseCaseFactory,
		PaydayUseCaseFactory,
		SendPayUseCaseFactory,
		ListEmployeesUseCaseFactory,
		GetEmployeeUseCaseFactory
{
}
