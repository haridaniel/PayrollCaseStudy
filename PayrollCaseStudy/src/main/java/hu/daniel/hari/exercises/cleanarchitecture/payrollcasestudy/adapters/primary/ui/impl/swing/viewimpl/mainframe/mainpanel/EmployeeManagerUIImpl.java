package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.mainpanel;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.mainpanel.employeemanager.EmployeeListUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.mainpanel.employeemanager.affiliation.AffiliationButtonUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.EmployeeManagerController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.EmployeeManagerUI;

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
