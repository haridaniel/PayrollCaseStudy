package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.AddSalesReceiptUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.AddServiceChargeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.DeleteEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.PaydayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddCommissionedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddHourlyEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeaffiliation.AddUnionMemberAffiliationUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeaffiliation.RemoveUnionMemberAffiliationUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeemployee.ChangeEmployeeNameUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeemployee.paymentmethod.ChangeToDirectPaymentMethodUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.changeemployee.paymentmethod.ChangeToHoldPaymentMethodUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.ListEmployeesUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.fulfiller.direct.DirectPaymentFulFiller;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.sendpay.SendPayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.sendpay.interactor.SendPayInteractorFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.moneytransfer.BankTransferPort;

public class UseCaseFactoryImpl implements UseCaseFactory {

	private EmployeeGateway employeeGateway;
	private TransactionalRunner transactionalRunner;
	private EntityFactory entityFactory;

	private SendPayInteractorFactory sendPayInteractorFactory;

	public UseCaseFactoryImpl(
			Database database, 
			BankTransferPort bankTransferPort
			) {
		this.transactionalRunner = database.transactionalRunner();
		this.employeeGateway = database.employeeGateway();
		this.entityFactory = database.entityFactory();
		sendPayInteractorFactory = new SendPayInteractorFactory(bankTransferPort);
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
	public PaydayUseCase paydayUseCase() {
		return new PaydayUseCase(transactionalRunner, employeeGateway);
	}

	@Override
	public ListEmployeesUseCase listEmployeesUseCase() {
		return new ListEmployeesUseCase(transactionalRunner, employeeGateway);
	}

	@Override
	public GetEmployeeUseCase getEmployeeUseCase() {
		return new GetEmployeeUseCase(transactionalRunner, employeeGateway);
	}

	@Override
	public SendPayUseCase sendPayUseCase() {
		return new SendPayUseCase(transactionalRunner, employeeGateway, sendPayInteractorFactory);
	}

	@Override
	public ChangeToDirectPaymentMethodUseCase changeToDirectPaymentMethodUseCase() {
		return new ChangeToDirectPaymentMethodUseCase(transactionalRunner, employeeGateway, entityFactory);
	}

	@Override
	public ChangeToHoldPaymentMethodUseCase changeToHoldPaymentMethodUseCase() {
		return new ChangeToHoldPaymentMethodUseCase(transactionalRunner, employeeGateway, entityFactory);
	}

}
