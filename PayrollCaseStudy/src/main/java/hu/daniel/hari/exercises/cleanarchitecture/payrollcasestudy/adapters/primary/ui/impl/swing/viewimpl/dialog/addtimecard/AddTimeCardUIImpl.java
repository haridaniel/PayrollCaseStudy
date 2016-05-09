package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addtimecard;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.MainFrameUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardController.AddTimeCardControllerFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardUI;

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
