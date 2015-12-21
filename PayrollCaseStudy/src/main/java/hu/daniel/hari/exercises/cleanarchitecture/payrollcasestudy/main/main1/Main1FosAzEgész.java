package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.main1;

import java.lang.reflect.Constructor;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.google.inject.persist.jpa.JpaPersistModule;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.inmemory.InMemoryDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.JPAPayrollDatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.JPAPersistenceUnitNames;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.PaydayUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.EmployeeGateway;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.TransactionalRunner;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddSalariedEmployeeRequest;

public class Main1FosAzEgész {

	public static void main(String[] args) {
		new Main1FosAzEgész();
	}
	
	public Main1FosAzEgész() {
	
		Database database = new InMemoryDatabase();
//		Database database = new JPAPayrollDatabaseModule(PersistenceUnitNames.POSTGRES_LOCAL_DB).getPayrollDatabase();
		
		
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl(database);
		
		PaydayUseCase paydayUseCase = useCaseFactory.create(PaydayUseCase.class);
		
		AddTimeCardUseCase addTimeCardUseCase = useCaseFactory.create(AddTimeCardUseCase.class);
		
		AddSalariedEmployeeUseCase addSalariedEmployeeUseCase = useCaseFactory.create(AddSalariedEmployeeUseCase.class);

		addSalariedEmployeeUseCase.execute(new AddSalariedEmployeeRequest(20, "name", "address", 5222));
		
		System.out.println();
	
	
	}
	
}
