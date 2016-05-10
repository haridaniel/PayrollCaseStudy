package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.exception.multiple;

import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.exception.UseCaseException;

public class MultipleErrorsUseCaseException extends UseCaseException {
	private final List<?> errors;

	public <E extends Error> MultipleErrorsUseCaseException(List<E> errors) {
		this.errors = errors;
	}
	
	/** Workaround for Java's deficiency: Throwable can't be generic **/
	public <E extends Error> List<E> getErrors() {
		return (List<E>) errors;
	}
}

