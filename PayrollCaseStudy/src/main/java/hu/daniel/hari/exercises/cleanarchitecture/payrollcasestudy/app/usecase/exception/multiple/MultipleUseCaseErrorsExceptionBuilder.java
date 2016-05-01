package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.exception.multiple;

import java.util.ArrayList;
import java.util.List;

public abstract class MultipleUseCaseErrorsExceptionBuilder<E extends Error> {
	private List<E> errors = new ArrayList<>();
	
	public MultipleUseCaseErrorsExceptionBuilder() throws MultipleUseCaseErrorsException {
		addErrors();
		throwIfThereAreErrors();
	}
	
	protected abstract void addErrors();

	protected void addError(E error) {
		errors.add(error);
	}
	
	public void throwIfThereAreErrors() {
		if(!errors.isEmpty())
			throw new MultipleUseCaseErrorsException(errors);
	}

}
