package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.dialog;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.JFrame;

import com.google.inject.assistedinject.Assisted;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addtimecard.AddTimeCardDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardController.AddTimeCardControllerFactory;

public class AddTimeCardDialogUI {
	
	private AddTimeCardDialog view;

	@Inject
	public AddTimeCardDialogUI(
			AddTimeCardControllerFactory controllerFactory,
			Provider<JFrame> rootFrameProvider,
			@Assisted int employeeId
			) {
		AddTimeCardController controller = controllerFactory.create(employeeId);
		view = new AddTimeCardDialog(rootFrameProvider.get());
		view.setViewListener(controller);
		controller.setView(view);
	}
	
	public void show() {
		view.showDialog();
	}
	
	public interface AddTimeCardDialogUIFactory {
		AddTimeCardDialogUI create(int employeeId);
	}
	
}
