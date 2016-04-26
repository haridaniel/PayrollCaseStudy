package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.UI;

public abstract class AddEmployeeUI<V extends AddEmployeeView> extends 
	UI<V, AddEmployeeController> 
{
	
	public AddEmployeeUI(AddEmployeeController controller) {
		super(controller);
	}

	public abstract void show();
	
	@Inject
	private void init() {
		System.out.println("init");
		view = getView();
		controller.setView(view);
	}
	

	protected abstract V getView();

	
}
