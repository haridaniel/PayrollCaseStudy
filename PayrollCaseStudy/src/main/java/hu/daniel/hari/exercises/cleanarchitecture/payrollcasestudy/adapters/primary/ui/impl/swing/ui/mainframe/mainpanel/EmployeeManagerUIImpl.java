package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel.employeemanager.EmployeeListUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.mainpanel.EmployeeManagerPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.EmployeeManagerController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.EmployeeManagerUI;

public class EmployeeManagerUIImpl extends EmployeeManagerUI<EmployeeManagerPanel>{

	@Inject
	public EmployeeManagerUIImpl(
			EmployeeManagerController controller,
			EmployeeListUIImpl employeeListUIImpl
			) {
		super(controller, employeeListUIImpl);
		setView(new EmployeeManagerPanel(employeeListUIImpl.view));
	}

}
