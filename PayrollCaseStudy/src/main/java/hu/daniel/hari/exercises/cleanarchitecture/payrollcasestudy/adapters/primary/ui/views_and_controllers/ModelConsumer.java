package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers;

public interface ModelConsumer<T> {
	void setModel(T viewModel);
}
