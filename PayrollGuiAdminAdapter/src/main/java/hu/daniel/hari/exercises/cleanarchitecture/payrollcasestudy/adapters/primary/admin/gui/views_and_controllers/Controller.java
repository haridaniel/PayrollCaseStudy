package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers;

public interface Controller<V extends View> {
	public void setView(V view);
}
