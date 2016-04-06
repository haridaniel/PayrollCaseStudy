package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.DefaultClosableViewController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.AddEmployeeViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeView.AddEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddSalariedEmployeeRequest;

public class AddEmployeeController extends DefaultClosableViewController<AddEmployeeView> implements AddEmployeeViewListener {

	private AddEmployeeUseCaseFactory useCaseFactory;
	private EventBus eventBus;
	private RequestCreator requestCreator = new RequestCreator();

	@Inject
	public AddEmployeeController(AddEmployeeUseCaseFactory addEmployeeUseCaseFactory, EventBus eventBus) {
		super(eventBus);
		this.useCaseFactory = addEmployeeUseCaseFactory;
		this.eventBus = eventBus;
	}

	@Override
	protected boolean isAllowedToCloseNow() {
		return true;
	}

	@Override
	public void onAddEmployee() {
		AddEmployeeViewModel model = getView().getModel();
		useCaseFactory.addSalariedEmployeeUseCase().execute(requestCreator.toRequest(model));
		eventBus.post(new AddedEmployeeEvent(model.employeeId, model.name));
		close();
	}

	@Override
	public void onCancel() {
		close();
	}
	
	private static class RequestCreator {
		public AddSalariedEmployeeRequest toRequest(AddEmployeeViewModel model) {
			int monthlySalary = 0; //TODO
			return new AddSalariedEmployeeRequest(
					model.employeeId, 
					model.name, 
					model.address, 
					monthlySalary
					);
		}
		
	}

}
