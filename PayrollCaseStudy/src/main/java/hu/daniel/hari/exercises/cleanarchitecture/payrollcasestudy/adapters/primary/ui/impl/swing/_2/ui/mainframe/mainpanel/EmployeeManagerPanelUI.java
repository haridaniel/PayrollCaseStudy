package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.ui.mainframe.mainpanel;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.ui.mainframe.mainpanel.employeemanager.EmployeeTablePanelUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.mainframe.mainpanel.EmployeeManagerPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactory;

public class EmployeeManagerPanelUI {

	public final EmployeeManagerPanel view;

	public EmployeeManagerPanelUI(EventBus eventBus, UseCaseFactory useCaseFactory) {
		EmployeeTablePanelUI employeeTablePanelUI = new EmployeeTablePanelUI(eventBus, useCaseFactory);
		
		view = new EmployeeManagerPanel(employeeTablePanelUI.view);
		
		EmployeeManagerController controller = new EmployeeManagerController(view, useCaseFactory, useCaseFactory, eventBus);
		view.setListener(controller);
	}
	

}
