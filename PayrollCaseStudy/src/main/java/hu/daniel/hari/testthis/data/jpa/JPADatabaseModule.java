package hu.daniel.hari.testthis.data.jpa;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class JPADatabaseModule {

	private JPADatabase jpaDatabase;

	public JPADatabaseModule(String persistenceUnitName) {
		Injector injector = createInjector(persistenceUnitName);
		startJpaPersistModule(injector);
		jpaDatabase = injector.getInstance(JPADatabase.class);
	}

	private Injector createInjector(String persistenceUnitName) {
		return Guice.createInjector(Stage.DEVELOPMENT,
				new GuiceModule2(),
				new JpaPersistModule(persistenceUnitName)
				);
	}

	private void startJpaPersistModule(Injector injector) {
		injector.getInstance(PersistService.class).start();
	}
	
	public Database getDatabase() {
		return jpaDatabase;
	}

	private class GuiceModule2 extends AbstractModule {
		
		@Override
		protected void configure() {
		}
		
	}
}
