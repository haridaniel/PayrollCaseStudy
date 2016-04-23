package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.dialog;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.JFrame;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee.AddEmployeeDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeController;

public class AddEmployeeDialogUI {
	
	private AddEmployeeDialog view;

	@Inject
	public AddEmployeeDialogUI(
			AddEmployeeController controller, 
			Provider<JFrame> rootFrameProvider
			) {
		view = new AddEmployeeDialog(rootFrameProvider.get());
		view.setViewListener(controller);
		controller.setView(view);
	}
	
	public void show() {
		view.showDialog();
	}
	
}
