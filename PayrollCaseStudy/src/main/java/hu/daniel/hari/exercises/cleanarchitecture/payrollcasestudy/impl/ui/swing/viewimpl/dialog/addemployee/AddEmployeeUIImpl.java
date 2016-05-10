package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.dialog.addemployee;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.MainFrameUIImpl;

public class AddEmployeeUIImpl extends 
	AddEmployeeUI<AddEmployeeDialog> 
{
	@Inject
	public AddEmployeeUIImpl(
			AddEmployeeController controller,
			MainFrameUIImpl mainFrameUIImpl
		) {
		super(controller, new AddEmployeeDialog(mainFrameUIImpl.view));
	}

}
