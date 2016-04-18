package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1;

import java.util.Optional;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.inmemory.InMemoryDatabase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.AddTimeCardUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddSalariedEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.paylist.PayListUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddSalariedEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class Main1FosAzEgész {

	public static void main(String[] args) {
		new Main1FosAzEgész();
	}
	
	public Main1FosAzEgész() {
	
		Database database = new InMemoryDatabase();
//		Database database = new JPAPayrollDatabaseModule(PersistenceUnitNames.POSTGRES_LOCAL_DB).getPayrollDatabase();
		
		
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl(database);
		
		PayListUseCase payListUseCase = useCaseFactory.create(PayListUseCase.class);
		
		AddTimeCardUseCase addTimeCardUseCase = useCaseFactory.create(AddTimeCardUseCase.class);
		
		AddSalariedEmployeeUseCase addSalariedEmployeeUseCase = useCaseFactory.create(AddSalariedEmployeeUseCase.class);

		addSalariedEmployeeUseCase.execute(new AddSalariedEmployeeRequest(20, "name", "address", 5222));
		
		System.out.println();
	
	
	}
	
}
