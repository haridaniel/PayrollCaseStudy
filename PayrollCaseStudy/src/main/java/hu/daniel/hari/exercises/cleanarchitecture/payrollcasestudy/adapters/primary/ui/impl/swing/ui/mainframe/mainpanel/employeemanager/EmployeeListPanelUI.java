package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel.employeemanager;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.mainpanel.employeemanager.EmployeeListPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeeListController;

public class EmployeeListPanelUI {

	public final EmployeeListPanel view;
	
	@Inject
	public EmployeeListPanelUI(
			EmployeeListController controller,
			EmployeeListPanel view
			) {
		this.view = view;
		view.setListener(controller);
		controller.setView(view);
	}
	

}
