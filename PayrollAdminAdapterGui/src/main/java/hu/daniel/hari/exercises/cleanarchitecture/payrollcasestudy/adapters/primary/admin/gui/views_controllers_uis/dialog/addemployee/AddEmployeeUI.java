package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.UI;

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
