package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.dialog;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.JFrame;

import com.google.inject.assistedinject.Assisted;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.MainFrameUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addunionmemberaffiliation.AddUnionMemberDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addunionmemberaffiliation.AddUnionMemberController.AddUnionMemberControllerFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addunionmemberaffiliation.AddUnionMemberUI;

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
