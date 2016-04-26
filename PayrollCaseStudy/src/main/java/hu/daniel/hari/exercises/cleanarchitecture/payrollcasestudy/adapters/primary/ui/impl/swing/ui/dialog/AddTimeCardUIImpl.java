package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.dialog;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.JFrame;

import com.google.inject.assistedinject.Assisted;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addtimecard.AddTimeCardDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardController.AddTimeCardControllerFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardUI;

public class AddTimeCardUIImpl extends AddTimeCardUI<AddTimeCardDialog> {
	
	private Provider<JFrame> rootFrameProvider;

	@Inject
	public AddTimeCardUIImpl(
			AddTimeCardControllerFactory controllerFactory,
			@Assisted int employeeId,
			Provider<JFrame> rootFrameProvider
			) {
		super(controllerFactory, employeeId);
		this.rootFrameProvider = rootFrameProvider;
	}
	
	public void show() {
		getView().showDialog();
	}

	@Override
	protected AddTimeCardDialog createView() {
		return new AddTimeCardDialog(rootFrameProvider.get());
	}
	
}
