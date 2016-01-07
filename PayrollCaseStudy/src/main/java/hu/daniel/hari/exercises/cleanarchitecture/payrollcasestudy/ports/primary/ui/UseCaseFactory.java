package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.AddSalesReceiptUseCase.AddSalesReceiptUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.AddServiceChargeUseCase.AddServiceChargeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.AddTimeCardUseCase.AddTimeCardUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.DeleteEmployeeUseCase.DeleteEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.PaydayUseCase.PaydayUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeaffiliation.AddUnionMemberAffiliationUseCase.AddUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeaffiliation.RemoveUnionMemberAffiliationUseCase.RemoveUnionMemberAffiliationUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeemployee.ChangeEmployeeUseCase.ChangeEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeemployee.paymentmethod.ChangeToAbstractPaymentMethodUseCase.ChangeToAbstractPaymentMethodUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase.GetEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.ListEmployeesUseCase.ListEmployeesUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.sendpay.SendPayUseCase.SendPayUseCaseFactory;

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
