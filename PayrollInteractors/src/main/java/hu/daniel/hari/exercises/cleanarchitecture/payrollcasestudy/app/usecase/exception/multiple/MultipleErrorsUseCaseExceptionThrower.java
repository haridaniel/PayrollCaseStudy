package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.exception.multiple;

import java.util.ArrayList;
import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.exception.multiple.Error;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.exception.multiple.MultipleErrorsUseCaseException;

public abstract class MultipleErrorsUseCaseExceptionThrower<E extends Error> {
	private List<E> errors = new ArrayList<>();
	
	public MultipleErrorsUseCaseExceptionThrower() throws MultipleErrorsUseCaseException {
		addErrors();
		throwIfThereAreErrors();
	}
	
	protected abstract void addErrors();

	protected void addError(E error) {
		errors.add(error);
	}
	
	public void throwIfThereAreErrors() {
		if(!errors.isEmpty())
			throw new MultipleErrorsUseCaseException(errors);
	}

}
