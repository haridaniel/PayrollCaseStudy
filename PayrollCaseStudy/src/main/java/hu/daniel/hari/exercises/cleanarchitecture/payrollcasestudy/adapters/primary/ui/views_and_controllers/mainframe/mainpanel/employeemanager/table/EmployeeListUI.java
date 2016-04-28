package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.table;

import java.time.LocalDate;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.UI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.ObservableSelectedEployeeItem;

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

	public void setObservableCurrentDate(ObservableValue<LocalDate> observableCurrentDate) {
		controller.setObservableCurrentDate(observableCurrentDate);
	}

	public ObservableSelectedEployeeItem getObservableSelectedEmployee() {
		return controller.getObservableSelectedEployeeId();
	}
	

}
