package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.addemployee;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.AddEmployeeController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee.AddEmployeeUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.MainFrameUIImpl;

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
