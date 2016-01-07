package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.UseCase;

public interface UseCaseFactory {
	<T extends UseCase<?>> T create(Class<T> useCaseType);
}
