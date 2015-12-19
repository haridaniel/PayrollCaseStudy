package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.EntityFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.Database;

public class JPAPayrollDatabaseModule {
	private JPADatabase jpaPayrollDatabase;

	public JPAPayrollDatabaseModule(String persistenceUnitName) {
		Injector injector = Guice.createInjector(Stage.DEVELOPMENT,
				new GuiceModule(),
				new JpaPersistModule(persistenceUnitName)
				);
		injector.getInstance(PersistService.class).start();

		jpaPayrollDatabase = injector.getInstance(JPADatabase.class);
	}
	
	public Database getPayrollDatabase() {
		return jpaPayrollDatabase;
	}

}

class GuiceModule extends AbstractModule {

	public GuiceModule() {
	}
	
	@Override
	protected void configure() {
		bind(EntityFactory.class).to(JPAAllEntityFactory.class);
	}
	
}