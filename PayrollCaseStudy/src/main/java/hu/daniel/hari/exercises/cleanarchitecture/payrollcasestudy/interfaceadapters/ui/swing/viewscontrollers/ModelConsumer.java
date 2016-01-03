package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers;

public interface ModelConsumer<T> {
	void setModel(T model);
}
