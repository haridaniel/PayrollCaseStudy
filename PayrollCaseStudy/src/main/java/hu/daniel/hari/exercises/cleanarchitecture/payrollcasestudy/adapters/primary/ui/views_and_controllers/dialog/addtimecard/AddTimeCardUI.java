package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.UI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardController.AddTimeCardControllerFactory;

public abstract class AddTimeCardUI<V extends AddTimeCardView> extends UI<V> {
	
	private AddTimeCardController controller;

	public AddTimeCardUI(
			AddTimeCardControllerFactory controllerFactory,
			int employeeId
			) {
		controller = controllerFactory.create(employeeId);
	}
	
	@Override
	protected void setView(V view) {
		view.setViewListener(controller);
		controller.setView(view);
	}
	
	public abstract void show();
	
	public interface AddTimeCardUIFactory {
		AddTimeCardUI<? extends AddTimeCardView> create(int employeeId);
	}
	
}
