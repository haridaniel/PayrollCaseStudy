package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addemployee;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.MainFrameUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeUI;

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
