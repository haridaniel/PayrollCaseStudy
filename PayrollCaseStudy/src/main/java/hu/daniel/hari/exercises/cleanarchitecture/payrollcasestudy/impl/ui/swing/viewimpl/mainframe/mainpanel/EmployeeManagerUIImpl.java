package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.mainframe.mainpanel;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.employeemanager.EmployeeManagerController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.employeemanager.EmployeeManagerUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.mainframe.mainpanel.employeemanager.EmployeeListUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.mainframe.mainpanel.employeemanager.affiliation.AffiliationButtonUIImpl;

public class EmployeeManagerUIImpl extends EmployeeManagerUI<EmployeeManagerPanel>{

	@Inject
	public EmployeeManagerUIImpl(
			EmployeeManagerController controller,
			EmployeeListUIImpl employeeListUIImpl,
			AffiliationButtonUIImpl affiliationButtonUIImpl
			) {
		super(controller, 
				new EmployeeManagerPanel(
					employeeListUIImpl.getView(),
					affiliationButtonUIImpl.getView()
					), 
			employeeListUIImpl,
			affiliationButtonUIImpl
			);
	}

}
