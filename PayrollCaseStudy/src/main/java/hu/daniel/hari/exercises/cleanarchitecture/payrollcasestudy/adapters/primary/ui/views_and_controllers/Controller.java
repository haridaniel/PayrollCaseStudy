package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers;

public interface Controller<V extends View> {
	public void setView(V view);
}
