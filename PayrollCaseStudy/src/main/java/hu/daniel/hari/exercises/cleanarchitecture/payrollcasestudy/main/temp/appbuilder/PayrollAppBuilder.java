package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.appbuilder;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.UseCaseFactoriesImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactories;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.secondary.database.Database;

public class PayrollAppBuilder {
	
	public static <T extends UI> T build(Database database, UIFactory<T> uiFactory) {
		UseCaseFactories useCaseFactories = new UseCaseFactoriesImpl(database, null);
		return uiFactory.createUI(useCaseFactories);
	}
	
	public interface UIFactory<T extends UI> {
		T createUI(UseCaseFactories useCaseFactories);
	}
	
	public interface UI {
		
	}
	
}
