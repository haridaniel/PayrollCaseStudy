package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.main2;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.JPAPayrollDatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.JPAPersistenceUnitNames;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.UseCaseFactoryImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.request.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.usecasefactory.UseCaseFactory;

public class Main2 {

	public static void main(String[] args) {
		new Main2();
	}
	
	public Main2() {
	
//		Database database = new InMemoryDatabase();
		Database database = new JPAPayrollDatabaseModule(JPAPersistenceUnitNames.POSTGRES_LOCAL_DB).getPayrollDatabase();
		database.transactionalRunner().executeInTransaction(() -> {
			database.employeeGateway().deleteAll();
		});
		
		
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl(database);
		
		useCaseFactory.addHourlyEmployeeUseCase().execute(
				new AddHourlyEmployeeRequest(11, "namee", "address", 10));
		
		useCaseFactory.addTimeCardUseCase().execute(new AddTimeCardRequest(11, LocalDate.now(), 5));
		useCaseFactory.addTimeCardUseCase().execute(new AddTimeCardRequest(11, LocalDate.now().minusDays(1), 8));
		useCaseFactory.addTimeCardUseCase().execute(new AddTimeCardRequest(11, LocalDate.now().minusDays(2), 8));
		
		
		System.out.println();
	
	
	}
	
	
}
