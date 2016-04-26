package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.UI;

public abstract class AddEmployeeUI<V extends AddEmployeeView> extends 
	UI<V, AddEmployeeController> 
{
	
	public AddEmployeeUI(AddEmployeeController controller, V view) {
		super(controller, view);
	}

	public void show() {
		controller.show();
	}
	
}
