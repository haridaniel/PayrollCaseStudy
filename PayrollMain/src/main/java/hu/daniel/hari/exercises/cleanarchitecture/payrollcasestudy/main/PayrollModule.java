package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.util.Providers;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.response.AffiliationTypeResponseFactoryImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.factories.usecase.UseCaseFactoriesImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.UseCaseFactories;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.employee.AffiliationTypeResponse.AffiliationTypeResponseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.banktransfer.BankTransferPort;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class PayrollModule extends AbstractModule {
	
	private Database database;
	private BankTransferPort bankTransferPort;
	
	private UseCaseFactories useCaseFactories;

	public PayrollModule(Database database, BankTransferPort bankTransferPort) {
		this.database = database;
		this.bankTransferPort = bankTransferPort;
		this.useCaseFactories = createUseCaseFactories();
	}

	private UseCaseFactories createUseCaseFactories() {
		return Guice.createInjector(this).getInstance(UseCaseFactories.class);
	}

	public UseCaseFactories getUseCaseFactories() {
		return useCaseFactories;
	}
	
	@Override
	protected void configure() {
		bind(Database.class).toProvider(Providers.of(database));
		bind(BankTransferPort.class).toProvider(Providers.of(bankTransferPort));

		bind(UseCaseFactories.class).to(UseCaseFactoriesImpl.class);
		bind(AffiliationTypeResponseFactory.class).to(AffiliationTypeResponseFactoryImpl.class);
	}
	
}
