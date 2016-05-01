package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addunionmemberaffiliation;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.UI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addunionmemberaffiliation.AddUnionMemberController.AddUnionMemberControllerFactory;

public class AddUnionMemberUI<V extends AddUnionMemberView> extends 
	UI<V, AddUnionMemberController> 
{
	public AddUnionMemberUI(
			AddUnionMemberControllerFactory controllerFactory, 
			V view, 
			int employeeId
			) {
		super(controllerFactory.create(employeeId), view);
	}
	
	public void show() {
		controller.show();
	}
	
	public interface AddUnionMemberUIFactory {
		AddUnionMemberUI<? extends AddUnionMemberView> create(int employeeId);
	}

}
