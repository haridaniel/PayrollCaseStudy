package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.mainframe.mainpanel.employeemanager;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.table.EmployeeListController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.table.EmployeeListUI;

public class EmployeeListUIImpl extends EmployeeListUI<EmployeeListPanel> {

	@Inject
	public EmployeeListUIImpl(
			EmployeeListController controller,
			EmployeeListPanel view
			) {
		super(controller, view);
	}

}
