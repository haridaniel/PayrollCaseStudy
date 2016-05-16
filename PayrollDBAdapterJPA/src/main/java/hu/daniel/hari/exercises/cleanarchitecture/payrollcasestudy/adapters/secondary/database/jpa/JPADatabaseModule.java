package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa;

import java.io.Closeable;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class JPADatabaseModule implements Closeable {
	private JPAPersistenceUnit jpaPersistenceUnit;
	private JPADatabase jpaDatabase;
	private PersistService persistService;

	private JPADatabaseModule(JPAPersistenceUnit jpaPersistenceUnit) {
		this.jpaPersistenceUnit = jpaPersistenceUnit;
		Injector injector = createInjector();
		jpaDatabase = injector.getInstance(JPADatabase.class);
		persistService = injector.getInstance(PersistService.class);
		persistService.start();
	}

	private Injector createInjector() {
		return Guice.createInjector(Stage.DEVELOPMENT,
				new GuiceModule(),
				new JpaPersistModule(jpaPersistenceUnit.name)
				);
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

	public static JPADatabaseModule createAndStart(JPAPersistenceUnit jpaPersistenceUnit) {
		return new JPADatabaseModule(jpaPersistenceUnit);
	}
}
