package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel;

import javax.inject.Inject;
import javax.inject.Provider;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.dialog.AddEmployeeDialogUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel.employeemanager.EmployeeListPanelUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.mainpanel.EmployeeManagerPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerController.EmployeeManagerControllerListener;

public class EmployeeManagerPanelUI {

	public final EmployeeManagerPanel view;
	
	@Inject
	public EmployeeManagerPanelUI(
			EmployeeManagerController controller,
			EmployeeListPanelUI employeeListPanelUI,
			Provider<AddEmployeeDialogUI> addEmployeeDialogUIProvider
			) {
		view = new EmployeeManagerPanel(employeeListPanelUI.view);
		view.setListener(controller);
		controller.setView(view);
		
		controller.setControllerListener(new EmployeeManagerControllerListener() {
			@Override
			public void openAddEmployeeDialog() {
				//int s = 5/0; //test exception
				addEmployeeDialogUIProvider.get().show();
			}
		});
		
	}
	
	
	

}
