package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.UI;

public abstract class AddEmployeeUI extends UI<AddEmployeeView, AddEmployeeController> {
	
	public AddEmployeeUI(AddEmployeeController controller) {
		super(controller);
	}

	public abstract void show();
	
}
