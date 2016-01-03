package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.HasListener;

public interface EmployeeManagerView extends HasListener<EmployeeManagerView.EmployeeManagerViewListener> {

	void enableButtons();
	void disableButtons();

	public static interface EmployeeManagerViewListener {
		void onDeleteAction();
	}

}