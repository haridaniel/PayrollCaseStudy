package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.UI;

public abstract class AddEmployeeUI<V extends AddEmployeeView> extends UI<V> {
	
	private AddEmployeeController controller;

	public AddEmployeeUI(
			AddEmployeeController controller 
			) {
		this.controller = controller;
	}

	@Override
	protected void setView(V view) {
		view.setViewListener(controller);
		controller.setView(view);
	}

	public abstract void show();
	
}
