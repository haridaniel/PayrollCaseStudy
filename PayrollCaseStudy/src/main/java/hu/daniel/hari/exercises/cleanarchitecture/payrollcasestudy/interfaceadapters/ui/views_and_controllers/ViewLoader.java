package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.views_and_controllers;

public interface ViewLoader {

	void loadMainFrameView();
	void loadAddEmployeeDialogView();
	void loadUncaugthExceptionView(Throwable e);

}