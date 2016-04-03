package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.HasListener;

public interface EmployeeManagerView extends HasListener<EmployeeManagerView.EmployeeManagerViewListener> {

	void setButtonsEnabled(boolean enabled);

	public static interface EmployeeManagerViewListener {
		void onDeleteAction();
		void onAddAction();
	}


}