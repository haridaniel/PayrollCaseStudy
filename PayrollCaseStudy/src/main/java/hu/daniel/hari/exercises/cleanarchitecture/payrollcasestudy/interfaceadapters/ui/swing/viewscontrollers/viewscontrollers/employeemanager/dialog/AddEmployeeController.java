package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.dialog;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.dialog.AddEmployeeView.AddEmployeeDialogListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.dialog.AddEmployeeView.AddEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.addemployee.AddSalariedEmployeeRequest;

public class AddEmployeeController implements AddEmployeeDialogListener {

	private AddEmployeeView view;
	private AddEmployeeUseCaseFactory useCaseFactory;
	private EventBus eventBus;

	public AddEmployeeController(AddEmployeeView view, AddEmployeeUseCaseFactory addEmployeeUseCaseFactory, EventBus eventBus) {
		this.view = view;
		this.useCaseFactory = addEmployeeUseCaseFactory;
		this.eventBus = eventBus;
		view.setListener(this);
	}

	@Override
	public void onAddEmployee() {
		AddSalariedEmployeeRequest addSalariedEmployeeRequest = new AddEmployeeDialogPresenter().toRequest(view.getModel());
		useCaseFactory.addSalariedEmployeeUseCase().execute(addSalariedEmployeeRequest);
		eventBus.post(new AddedEmployeeEvent(addSalariedEmployeeRequest.employeeId, addSalariedEmployeeRequest.name));
		close();
	}

	@Override
	public void onCancel() {
		close();
	}
	
	private void close() {
		view.close();
	}
	
	private static class AddEmployeeDialogPresenter {
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
