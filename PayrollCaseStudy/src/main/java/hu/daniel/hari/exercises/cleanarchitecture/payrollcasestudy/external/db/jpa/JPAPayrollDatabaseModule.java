package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.PayrollDatabase;

public class JPAPayrollDatabaseModule {
//	private static final String PERSISTENCE_UNIT_NAME = "testDB";
	private static final String PERSISTENCE_UNIT_NAME = "postgresTest";
	
	private JPAPayrollDatabase jpaPayrollDatabase;
	@Deprecated
	public static Injector INJECTOR;

	public JPAPayrollDatabaseModule() {
		Injector injector = Guice.createInjector(Stage.DEVELOPMENT,
				new GuiceModule(),
				new JpaPersistModule(PERSISTENCE_UNIT_NAME)
				);
		injector.getInstance(PersistService.class).start();

		INJECTOR = injector;
		
		jpaPayrollDatabase = injector.getInstance(JPAPayrollDatabase.class);
		
	}
	
	public PayrollDatabase getPayrollDatabase() {
		return jpaPayrollDatabase;
	}

}

class GuiceModule extends AbstractModule {

	public GuiceModule() {
	}
	
	@Override
	protected void configure() {
	}
	
}