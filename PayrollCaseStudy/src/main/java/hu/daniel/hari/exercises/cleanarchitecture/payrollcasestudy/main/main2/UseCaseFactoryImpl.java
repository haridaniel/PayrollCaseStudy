package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.main2;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddSalesReceiptUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddSalesReceiptUseCase.AddSalesReceiptUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddServiceChargeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.DeleteEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.PaydayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee.AddCommissionedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee.AddHourlyEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeaffiliation.AddUnionMemberAffiliationUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeaffiliation.RemoveUnionMemberAffiliationUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.changeemployee.ChangeEmployeeNameUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.interfaces.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.interfaces.details.EntityFactory;

public class UseCaseFactoryImpl implements UseCaseFactory {
	
	private EmployeeGateway employeeGateway;
	private TransactionalRunner transactionalRunner;
	private EntityFactory entityFactory;

	public UseCaseFactoryImpl(Database database) {
		this.transactionalRunner = database.transactionalRunner();
		this.employeeGateway = database.employeeGateway();
		this.entityFactory = database.entityFactory();
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
	
}
