package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.table;

import java.time.LocalDate;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.UI;

public class EmployeeListUI<V extends EmployeeListView> extends UI<V, EmployeeListController> {

	@Inject
	public EmployeeListUI(
			EmployeeListController controller
			) {
		super(controller);
	}

	public void setObservableCurrentDate(ObservableValue<LocalDate> observableCurrentDate) {
		controller.setObservableCurrentDate(observableCurrentDate);
	}

	public ObservableSelectedEployeeItem getObservableSelectedEployeeId() {
		return controller.getObservableSelectedEployeeId();
	}
	

}
