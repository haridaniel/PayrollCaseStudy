package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers;

public interface ViewLoader {

	void loadMainFrameView();
	void loadAddEmployeeDialogView();
	void loadUncaugthExceptionView(Throwable e);

}