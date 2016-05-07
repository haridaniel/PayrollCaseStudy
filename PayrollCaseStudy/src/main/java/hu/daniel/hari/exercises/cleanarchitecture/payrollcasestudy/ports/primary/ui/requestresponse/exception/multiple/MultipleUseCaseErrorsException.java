package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.exception.multiple;

import java.util.List;

public class MultipleUseCaseErrorsException extends RuntimeException {
	private final List<?> errors;

	public <E extends Error> MultipleUseCaseErrorsException(List<E> errors) {
		this.errors = errors;
	}
	
	/** Workaround for Java's deficiency: Throwable can't be generic **/
	public <E extends Error> List<E> getErrors() {
		return (List<E>) errors;
	}
}

