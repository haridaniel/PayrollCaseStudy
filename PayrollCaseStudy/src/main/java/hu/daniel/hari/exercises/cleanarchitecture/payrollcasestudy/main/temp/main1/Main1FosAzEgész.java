package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1;

import java.lang.reflect.Constructor;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.google.inject.persist.jpa.JpaPersistModule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.InMemoryDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPADatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.JPAPersistenceUnitNames;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.generate.GeneratePayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.TransactionalRunner;

public class Main1FosAzEgész {

	public static void main(String[] args) {
		new Main1FosAzEgész();
	}
	
	public Main1FosAzEgész() {
	
		Database database = new InMemoryDatabase();
//		Database database = new JPAPayrollDatabaseModule(PersistenceUnitNames.POSTGRES_LOCAL_DB).getPayrollDatabase();
		
		
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl(database);
		
		GeneratePayUseCase generatePayUseCase = useCaseFactory.create(GeneratePayUseCase.class);
		
		AddTimeCardUseCase addTimeCardUseCase = useCaseFactory.create(AddTimeCardUseCase.class);
		
		AddSalariedEmployeeUseCase addSalariedEmployeeUseCase = useCaseFactory.create(AddSalariedEmployeeUseCase.class);

		addSalariedEmployeeUseCase.execute(new AddSalariedEmployeeRequest(20, "name", "address", 5222));
		
		System.out.println();
	
	
	}
	
}
