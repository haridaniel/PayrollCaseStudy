package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.main1;

import javax.inject.Inject;

import com.google.inject.Injector;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.usecase.UseCase;

public class UseCaseFactoryImpl implements UseCaseFactory {

	@Inject Injector injector;
	
	@Inject
	public UseCaseFactoryImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public <T extends UseCase<?>> T create(Class<T> useCaseType) {
		
		
		
		return null;
	}

}
