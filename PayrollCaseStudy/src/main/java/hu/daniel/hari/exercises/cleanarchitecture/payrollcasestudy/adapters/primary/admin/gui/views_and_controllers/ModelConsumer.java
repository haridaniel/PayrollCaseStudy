package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers;

public interface ModelConsumer<T> {
	void setModel(T viewModel);
}
