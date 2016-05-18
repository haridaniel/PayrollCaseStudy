package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.table;

import java.time.LocalDate;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.Observable;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.UI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.ObservableSelectedEployeeItem;

public abstract class EmployeeListUI<V extends EmployeeListView> extends 
	UI<V, EmployeeListController> 
{

	@Inject
	public EmployeeListUI(
			EmployeeListController controller, 
			V view
			) {
		super(controller, view);
	}

	public void setObservableCurrentDate(Observable<LocalDate> observableCurrentDate) {
		controller.setObservableCurrentDate(observableCurrentDate);
	}

	public ObservableSelectedEployeeItem getObservableSelectedEmployee() {
		return controller.getObservableSelectedEployeeId();
	}
	

}
