package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.inject.Provider;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.dialog.AddEmployeeDialogUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel.employeemanager.EmployeeListPanelUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.mainpanel.EmployeeManagerPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerController;

public class EmployeeManagerPanelUI {

	public final EmployeeManagerPanel view;
	private EmployeeManagerController controller;
	private EmployeeListPanelUI employeeListPanelUI;
	
	@Inject
	public EmployeeManagerPanelUI(
			EmployeeManagerController controller,
			EmployeeListPanelUI employeeListPanelUI,
			Provider<AddEmployeeDialogUI> addEmployeeDialogUIProvider
			) {
		this.employeeListPanelUI = employeeListPanelUI;
		view = new EmployeeManagerPanel(employeeListPanelUI.view);
		view.setListener(controller);
		controller.setView(view);
		controller.setObservableSelectedEployeeId(employeeListPanelUI.getObservableSelectedEployeeId());
		
	}

	public void setObservableCurrentDate(ObservableValue<LocalDate> observableCurrentDate) {
		employeeListPanelUI.setObservableCurrentDate(observableCurrentDate);
	}
	
	
	

}
