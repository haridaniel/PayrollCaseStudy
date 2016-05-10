package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.common;

import java.util.List;
import java.util.stream.Collectors;

public abstract class MultipleFormatter<T>  {

	public List<String> formatAll(List<T> elements) {
		return elements.stream()
				.map(element -> format(element))
				.collect(Collectors.toList());
	}

	protected abstract String format(T element);

}
