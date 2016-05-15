package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa;

import java.io.Closeable;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class JPADatabaseModule implements Closeable{

	private JPADatabase jpaDatabase;
	private PersistService persistService;

	public JPADatabaseModule(String persistenceUnitName) {
		Injector injector = createInjector(persistenceUnitName);
		jpaDatabase = injector.getInstance(JPADatabase.class);
		persistService = injector.getInstance(PersistService.class);
		startJpaPersistModule();
	}

	private Injector createInjector(String persistenceUnitName) {
		return Guice.createInjector(Stage.DEVELOPMENT,
				new GuiceModule(),
				new JpaPersistModule(persistenceUnitName)
				);
	}

	private void startJpaPersistModule() {
		persistService.start();
	}
	
	@Override
	public void close() {
		persistService.stop();
	}

	public Database getDatabase() {
		return jpaDatabase;
	}

	private class GuiceModule extends AbstractModule {
		
		@Override
		protected void configure() {
		}
		
	}
}
