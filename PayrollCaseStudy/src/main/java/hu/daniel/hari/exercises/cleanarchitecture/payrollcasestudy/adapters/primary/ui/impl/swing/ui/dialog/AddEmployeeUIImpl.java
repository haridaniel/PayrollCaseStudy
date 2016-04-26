package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.dialog;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.JFrame;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee.AddEmployeeDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView;

public class AddEmployeeUIImpl extends 
	AddEmployeeUI<AddEmployeeDialog> 
{

	private Provider<JFrame> rootFrameProvider;

	@Inject
	public AddEmployeeUIImpl(
			AddEmployeeController controller,
			Provider<JFrame> rootFrameProvider
		) {
		super(controller);
//		setView(new AddEmployeeDialog(rootFrameProvider.get()));
		this.rootFrameProvider = rootFrameProvider;
	}

	@Override
	public void show() {
		view.showDialog();
	}

	@Override
	protected AddEmployeeDialog getView() {
		return new AddEmployeeDialog(rootFrameProvider.get());
	}
	
	
	
	
}
