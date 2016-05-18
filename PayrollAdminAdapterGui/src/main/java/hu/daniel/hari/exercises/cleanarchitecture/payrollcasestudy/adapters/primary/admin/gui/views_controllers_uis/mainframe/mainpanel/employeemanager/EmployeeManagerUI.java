package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.Observable;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.UI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.affiliationbutton.AffiliationButtonUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.table.EmployeeListUI;

public abstract class EmployeeManagerUI<V extends EmployeeManagerView> extends 
	UI<V, EmployeeManagerController> 
{

	private EmployeeListUI<?> employeeListUI;
	
	public EmployeeManagerUI(
			EmployeeManagerController controller,
			V view,
			EmployeeListUI<?> employeeListUI, 
			AffiliationButtonUI<?> affiliationButtonUI
			) {
		super(controller, view);
		this.employeeListUI = employeeListUI;
		controller.setObservableSelectedEmployee(employeeListUI.getObservableSelectedEmployee());
		affiliationButtonUI.setObservableSelectedEmployee(employeeListUI.getObservableSelectedEmployee());
	}

	public void setObservableCurrentDate(Observable<LocalDate> observableCurrentDate) {
		employeeListUI.setObservableCurrentDate(observableCurrentDate);
	}

}
