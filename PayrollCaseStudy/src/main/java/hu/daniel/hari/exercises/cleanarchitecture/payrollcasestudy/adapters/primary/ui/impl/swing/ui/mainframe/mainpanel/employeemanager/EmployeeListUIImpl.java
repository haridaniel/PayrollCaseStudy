package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel.employeemanager;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.mainpanel.employeemanager.EmployeeListPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.table.EmployeeListController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.table.EmployeeListUI;

public class EmployeeListUIImpl extends EmployeeListUI<EmployeeListPanel> {

	@Inject
	public EmployeeListUIImpl(
			EmployeeListController controller,
			EmployeeListPanel view
			) {
		super(controller);
		setView(view);
	}

}
