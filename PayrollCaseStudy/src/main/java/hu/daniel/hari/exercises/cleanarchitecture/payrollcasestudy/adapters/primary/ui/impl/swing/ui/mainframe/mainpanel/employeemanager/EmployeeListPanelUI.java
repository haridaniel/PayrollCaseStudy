package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel.employeemanager;

import java.time.LocalDate;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.mainpanel.employeemanager.EmployeeListPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeeListController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.ObservableSelectedEployeeId;

public class EmployeeListPanelUI {

	public final EmployeeListPanel view;
	private EmployeeListController controller;
	
	@Inject
	public EmployeeListPanelUI(
			EmployeeListController controller,
			EmployeeListPanel view
			) {
		this.controller = controller;
		this.view = view;
		view.setListener(controller);
		controller.setView(view);
	}

	public void setObservableCurrentDate(ObservableValue<LocalDate> observableCurrentDate) {
		controller.setObservableCurrentDate(observableCurrentDate);
	}

	public ObservableSelectedEployeeId getObservableSelectedEployeeId() {
		return controller.getObservableSelectedEployeeId();
	}
	

}
