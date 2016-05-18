package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.dialog.addtimecard;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addtimecard.AddTimeCardUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addtimecard.AddTimeCardController.AddTimeCardControllerFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.MainFrameUIImpl;

public class AddTimeCardUIImpl extends AddTimeCardUI<AddTimeCardDialog> {
	
	@Inject
	public AddTimeCardUIImpl(
			AddTimeCardControllerFactory controllerFactory,
			@Assisted int employeeId,
			MainFrameUIImpl mainFrameUIImpl
			) {
		super(controllerFactory, new AddTimeCardDialog(mainFrameUIImpl.view), employeeId);
	}
	
}
