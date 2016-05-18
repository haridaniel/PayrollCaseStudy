package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addtimecard;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.UI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.dialog.addtimecard.AddTimeCardController.AddTimeCardControllerFactory;

public abstract class AddTimeCardUI<V extends AddTimeCardView> extends 
	UI<V, AddTimeCardController> 
{
	
	public AddTimeCardUI(
			AddTimeCardControllerFactory controllerFactory,
			V view,
			int employeeId
			) {
		super(controllerFactory.create(employeeId), view);
	}
	
	public void show() {
		controller.show();
	}
	
	public interface AddTimeCardUIFactory {
		AddTimeCardUI<? extends AddTimeCardView> create(int employeeId);
	}
	
}
