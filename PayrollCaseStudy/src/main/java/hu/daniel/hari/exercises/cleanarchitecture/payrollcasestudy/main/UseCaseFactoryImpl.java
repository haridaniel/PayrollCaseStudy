package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.AddSalesReceiptUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.AddServiceChargeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.DeleteEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.EmployeesOverviewUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.PaydayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.AddSalesReceiptUseCase.AddSalesReceiptUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddCommissionedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddHourlyEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.changeaffiliation.AddUnionMemberAffiliationUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.changeaffiliation.RemoveUnionMemberAffiliationUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.changeemployee.ChangeEmployeeNameUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.usecasefactory.UseCaseFactory;

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

	@Override
	public EmployeesOverviewUseCase employeesOverviewUseCase() {
		return new EmployeesOverviewUseCase(transactionalRunner, employeeGateway);
	}
	
}
