package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.dialog;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.JFrame;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.dialog.addemployee.AddEmployeeDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.dialog.addemployee.AddEmployeeController;

public class AddEmployeeDialogUI {
	
	private AddEmployeeDialog view;

	@Inject
	public AddEmployeeDialogUI(
			AddEmployeeController controller, 
			Provider<JFrame> rootFrameProvider
			) {
		view = new AddEmployeeDialog(rootFrameProvider.get());
		view.setListener(controller);
		controller.setView(view);
	}
	
	public void show() {
		view.setVisible(true);
	}
	
}
