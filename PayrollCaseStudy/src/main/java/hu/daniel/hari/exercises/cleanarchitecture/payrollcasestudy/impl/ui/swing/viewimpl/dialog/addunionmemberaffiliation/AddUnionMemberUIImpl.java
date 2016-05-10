package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.dialog.addunionmemberaffiliation;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.JFrame;

import com.google.inject.assistedinject.Assisted;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addunionmemberaffiliation.AddUnionMemberUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog.addunionmemberaffiliation.AddUnionMemberController.AddUnionMemberControllerFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.MainFrameUIImpl;

public class AddUnionMemberUIImpl extends 
	AddUnionMemberUI<AddUnionMemberDialog> 
{
	@Inject
	public AddUnionMemberUIImpl(
			AddUnionMemberControllerFactory controllerFactory, 
			@Assisted int employeeId,
			MainFrameUIImpl mainFrameUIImpl
			) {
		super(controllerFactory, new AddUnionMemberDialog(mainFrameUIImpl.view), employeeId);
	}

}
