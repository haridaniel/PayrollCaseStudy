package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factory_impl;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.AddSalesReceiptUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.AddServiceChargeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.DeleteEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddCommissionedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddHourlyEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.affiliation.unionmember.AddUnionMemberAffiliationUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.affiliation.unionmember.GetUnionMemberAffiliationUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.affiliation.unionmember.RemoveUnionMemberAffiliationUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeemployee.ChangeEmployeeNameUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeemployee.paymentmethod.ChangeToDirectPaymentMethodUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeemployee.paymentmethod.ChangeToPaymasterPaymentMethodUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.EmployeeListUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.fullfill.PaymentFulfillUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.fullfill.fullfillers.PaymentFulfillerFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.fullfill.fullfillers.PaymentFulfillerFactoryImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.paylist.PayListUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.timecard.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.timecard.UpdateTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecasefactories.UseCaseFactories;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.banktransfer.BankTransferPort;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class UseCaseFactoriesImpl implements UseCaseFactories {

	private EmployeeGateway employeeGateway;
	private TransactionalRunner transactionalRunner;
	private EntityFactory entityFactory;

	private PaymentFulfillerFactory paymentFulfillerFactory;

	public UseCaseFactoriesImpl(
			Database database, 
			BankTransferPort bankTransferPort
			) {
		this.transactionalRunner = database.transactionalRunner();
		this.employeeGateway = database.employeeGateway();
		this.entityFactory = database.entityFactory();
		
		paymentFulfillerFactory = new PaymentFulfillerFactoryImpl(bankTransferPort, transactionalRunner);
	}

	@Override
	public AddSalariedEmployeeUseCase addSalariedEmployeeUseCase() {
		return new AddSalariedEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory);
	}

	@Override
	public AddHourlyEmployeeUseCase addHourlyEmployeeUseCase() {
		return new AddHourlyEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory);
	}

	@Override
	public AddCommissionedEmployeeUseCase addCommissionedEmployeeUseCase() {
		return new AddCommissionedEmployeeUseCase(transactionalRunner, employeeGateway, entityFactory, entityFactory, entityFactory, entityFactory, entityFactory);
	}

	@Override
	public AddTimeCardUseCase addTimeCardUseCase() {
		return new AddTimeCardUseCase(transactionalRunner, employeeGateway, entityFactory);
	}

	@Override
	public UpdateTimeCardUseCase updateTimeCardUseCase() {
		return new UpdateTimeCardUseCase(transactionalRunner, employeeGateway);
	}

	@Override
	public GetUnionMemberAffiliationUseCase getUnionMemberAffiliationUseCase() {
		return new GetUnionMemberAffiliationUseCase(transactionalRunner, employeeGateway);
	}

	@Override
	public AddUnionMemberAffiliationUseCase addUnionMemberAffiliationUseCase() {
		return new AddUnionMemberAffiliationUseCase(transactionalRunner, employeeGateway, entityFactory);
	}

	@Override
	public RemoveUnionMemberAffiliationUseCase removeUnionMemberAffiliationUseCase() {
		return new RemoveUnionMemberAffiliationUseCase(transactionalRunner, employeeGateway, entityFactory);
	}

	@Override
	public ChangeEmployeeNameUseCase changeEmployeeNameUseCase() {
		return new ChangeEmployeeNameUseCase(transactionalRunner, employeeGateway);
	}

	@Override
	public AddSalesReceiptUseCase addSalesReceiptUseCaseFactory() {
		return new AddSalesReceiptUseCase(transactionalRunner, employeeGateway, entityFactory);
	}

	@Override
	public AddServiceChargeUseCase addServiceChargeUseCase() {
		return new AddServiceChargeUseCase(transactionalRunner, employeeGateway, entityFactory);
	}

	@Override
	public DeleteEmployeeUseCase deleteEmployeeUseCase() {
		return new DeleteEmployeeUseCase(transactionalRunner, employeeGateway);
	}

	@Override
	public PayListUseCase payListUseCase() {
		return new PayListUseCase(transactionalRunner, employeeGateway);
	}

	@Override
	public EmployeeListUseCase employeeListUseCase() {
		return new EmployeeListUseCase(transactionalRunner, employeeGateway);
	}

	@Override
	public GetEmployeeUseCase getEmployeeUseCase() {
		return new GetEmployeeUseCase(transactionalRunner, employeeGateway);
	}

	@Override
	public PaymentFulfillUseCase paymentFulfillUseCase() {
		return new PaymentFulfillUseCase(employeeGateway, paymentFulfillerFactory);
	}

	@Override
	public ChangeToDirectPaymentMethodUseCase changeToDirectPaymentMethodUseCase() {
		return new ChangeToDirectPaymentMethodUseCase(transactionalRunner, employeeGateway, entityFactory);
	}

	@Override
	public ChangeToPaymasterPaymentMethodUseCase changeToPaymasterPaymentMethodUseCase() {
		return new ChangeToPaymasterPaymentMethodUseCase(transactionalRunner, employeeGateway, entityFactory);
	}

}
