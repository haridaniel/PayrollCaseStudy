package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.appbuilder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.UseCaseFactoryImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.database.Database;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.usecasefactory.UseCaseFactory;

public class PayrollAppBuilder {
	
	public static <T extends UI> T build(Database database, UIFactory<T> uiFactory) {
		UseCaseFactory useCaseFactory = new UseCaseFactoryImpl(database);
		return uiFactory.createUI(useCaseFactory);
	}
	
	public interface UIFactory<T extends UI> {
		T createUI(UseCaseFactory useCaseFactory);
	}
	
	public interface UI {
		
	}
	
}
