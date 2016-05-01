package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.exception.multiple;

import java.util.List;

public class MultipleUseCaseErrorsException extends RuntimeException {
	private final List<?> errors;

	public <E> MultipleUseCaseErrorsException(List<E> errors) {
		this.errors = errors;
	}
	
	/** Workaround for Java's deficiency: Throwable can't be generic **/
	public <E> List<E> getErrors() {
		return (List<E>) errors;
	}
}

