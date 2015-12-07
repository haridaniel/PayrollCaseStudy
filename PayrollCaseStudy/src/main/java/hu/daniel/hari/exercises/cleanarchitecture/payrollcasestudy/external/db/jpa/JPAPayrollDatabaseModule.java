package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.db.PayrollDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.HourlyJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.EmployeeProxy.EmployeeProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.HourlyPaymentClassificationProxy.HourlyPaymentClassificationProxyFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

public class JPAPayrollDatabaseModule {
//	private static final String PERSISTENCE_UNIT_NAME = "testDB";
	private static final String PERSISTENCE_UNIT_NAME = "postgresTest";
	
	private JPAPayrollDatabase jpaPayrollDatabase;

	public JPAPayrollDatabaseModule() {
		Injector injector = Guice.createInjector(Stage.DEVELOPMENT,
				new GuiceModule(),
				new JpaPersistModule(PERSISTENCE_UNIT_NAME)
				);
		injector.getInstance(PersistService.class).start();

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
		proxyFactories();
	}

	private void proxyFactories() {
		install(new FactoryModuleBuilder().build(EmployeeProxyFactory.class));
		install(new FactoryModuleBuilder().build(HourlyPaymentClassificationProxyFactory.class));
	}
	
}