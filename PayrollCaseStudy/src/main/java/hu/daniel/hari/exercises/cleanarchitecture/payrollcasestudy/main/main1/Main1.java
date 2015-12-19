package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.main1;

import java.lang.reflect.Constructor;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.google.inject.persist.jpa.JpaPersistModule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.db.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.PaydayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee.AddEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.impl.inmemory.InMemoryDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.interfaces.Database;

public class Main1 {

	public static void main(String[] args) {
		new Main1();
	}
	
	public Main1() {
	
		InMemoryDatabase inMemoryDatabase = new InMemoryDatabase();
		
		Injector injector = Guice.createInjector(Stage.DEVELOPMENT, 
				new MainModule(inMemoryDatabase)
				);		
		
		PaydayUseCase instance = injector.getInstance(PaydayUseCase.class);
		
		UseCaseFactory useCaseFactoryImpl = injector.getInstance(UseCaseFactoryImpl.class);
		
		
		
		
//		AddSalariedEmployeeUseCase addSalariedEmployeeUseCase = useCaseFactoryImpl.create(AddSalariedEmployeeUseCase.class);
		
		System.out.println();
	
	
	}
	
	public class MainModule extends AbstractModule {

		private Database database;

		public MainModule(Database database) {
			this.database = database;
		}

		@Override
		protected void configure() {
			bind(PaydayUseCase.class).toConstructor((Constructor<PaydayUseCase>) PaydayUseCase.class.getConstructors()[0]);
			
			bind(TransactionalRunner.class).toInstance(database.transactionalRunner());
			bind(EmployeeGateway.class).toInstance(database.employeeGateway());
			
		}
		
	}
	
}
