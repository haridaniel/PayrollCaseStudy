package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.views_and_controllers.mainframe.employeemanager.dialog;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.views_and_controllers.mainframe.employeemanager.dialog.AddEmployeeView.AddEmployeeDialogListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.views_and_controllers.mainframe.employeemanager.dialog.AddEmployeeView.AddEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.usecases.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.addemployee.AddSalariedEmployeeRequest;

public class AddEmployeeController implements AddEmployeeDialogListener {

	private AddEmployeeView view;
	private AddEmployeeUseCaseFactory useCaseFactory;
	private EventBus eventBus;
	private ModelConverter modelConverter = new ModelConverter();

	public AddEmployeeController(AddEmployeeView view, AddEmployeeUseCaseFactory addEmployeeUseCaseFactory, EventBus eventBus) {
		this.view = view;
		this.useCaseFactory = addEmployeeUseCaseFactory;
		this.eventBus = eventBus;
	}

	@Override
	public void onAddEmployee() {
		AddEmployeeViewModel model = view.getModel();
		useCaseFactory.addSalariedEmployeeUseCase().execute(modelConverter.toRequest(model));
		eventBus.post(new AddedEmployeeEvent(model.employeeId, model.name));
		close();
	}

	@Override
	public void onCancel() {
		close();
	}
	
	private void close() {
		view.close();
	}
	
	private static class ModelConverter {
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
