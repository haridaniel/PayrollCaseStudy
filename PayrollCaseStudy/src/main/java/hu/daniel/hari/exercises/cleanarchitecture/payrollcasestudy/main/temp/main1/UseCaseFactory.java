package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.UseCase;

public interface UseCaseFactory {
	<T extends UseCase<?>> T create(Class<T> useCaseType);
}
