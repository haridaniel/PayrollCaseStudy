package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.viewscontrollers.employeemanager.dialog;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.events.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.events.EmployeeCountChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.viewscontrollers.employeemanager.dialog.AddEmployeeDialogView.AddEmployeeDialogListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.addemployee.AddSalariedEmployeeRequest;

public class AddEmployeeDialogController implements AddEmployeeDialogListener {

	private AddEmployeeDialogView dialog;
	private AddEmployeeUseCaseFactory useCaseFactory;
	private EventBus eventBus;

	public AddEmployeeDialogController(AddEmployeeDialogView dialog, AddEmployeeUseCaseFactory addEmployeeUseCaseFactory, EventBus eventBus) {
		this.dialog = dialog;
		this.useCaseFactory = addEmployeeUseCaseFactory;
		this.eventBus = eventBus;
		dialog.setListener(this);
	}

	@Override
	public void onAddEmployee() {
		AddSalariedEmployeeRequest request = new AddEmployeeDialogPresenter().toRequest(dialog.getModel());
		useCaseFactory.addSalariedEmployeeUseCase().execute(request);
		eventBus.post(new AddedEmployeeEvent(request.employeeId, request.name));
		closeDialog();
	}

	@Override
	public void onCancel() {
		closeDialog();
	}
	
	private void closeDialog() {
		dialog.dispose();
	}
	
	private static class AddEmployeeDialogPresenter {
		public AddSalariedEmployeeRequest toRequest(AddEmployeeDialogViewModel model) {
			int monthlySalary = 0;
			return new AddSalariedEmployeeRequest(
					model.employeeId, 
					model.name, 
					model.address, 
					monthlySalary
					);
		}
		
	}

}
