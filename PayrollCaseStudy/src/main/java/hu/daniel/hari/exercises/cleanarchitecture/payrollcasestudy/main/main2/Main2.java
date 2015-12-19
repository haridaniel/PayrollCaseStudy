package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.main2;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.AddTimeCardRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.boundary.userapi.requestmodels.addemployee.AddHourlyEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.impl.jpa.JPAPayrollDatabaseModule;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.modul.database.interfaces.Database;

public class Main2 {

	public static void main(String[] args) {
		new Main2();
	}
	
	public Main2() {
	
//		Database database = new InMemoryDatabase();

		Database database = new JPAPayrollDatabaseModule("postgres-local-db").getPayrollDatabase();
		database.transactionalRunner().executeInTransaction(() -> {
			database.employeeGateway().deleteAllEmployees();
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
