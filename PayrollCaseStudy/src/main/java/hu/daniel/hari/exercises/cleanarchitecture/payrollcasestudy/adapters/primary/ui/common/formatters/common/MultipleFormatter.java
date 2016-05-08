package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.common;

import java.util.List;
import java.util.stream.Collectors;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.add.AddEmployeeError;

public abstract class MultipleFormatter<T>  {

	public List<String> formatAll(List<T> elements) {
		return elements.stream()
				.map(element -> format(element))
				.collect(Collectors.toList());
	}

	protected abstract String format(T element);

}
