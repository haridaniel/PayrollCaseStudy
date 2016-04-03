package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.dialog;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.AddedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.DeletedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing._2.viewimpl.mainframe.dialogs.AddEmployeeDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.dialog.AddEmployeeView.AddEmployeeDialogListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.dialog.AddEmployeeView.AddEmployeeViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.addemployee.AddEmployeeUseCase.AddEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.addemployee.AddSalariedEmployeeRequest;

public class AddEmployeeController implements AddEmployeeDialogListener {

	private AddEmployeeView view;
	private AddEmployeeUseCaseFactory useCaseFactory;
	private EventBus eventBus;
	private RequestCreator requestCreator = new RequestCreator();

	@Inject
	public AddEmployeeController(AddEmployeeUseCaseFactory addEmployeeUseCaseFactory, EventBus eventBus) {
		this.useCaseFactory = addEmployeeUseCaseFactory;
		this.eventBus = eventBus;
		eventBus.register(this);
	}

	@Override
	public void onAddEmployee() {
		AddEmployeeViewModel model = view.getModel();
		useCaseFactory.addSalariedEmployeeUseCase().execute(requestCreator.toRequest(model));
		eventBus.post(new AddedEmployeeEvent(model.employeeId, model.name));
		close();
	}

	@Deprecated
	@Subscribe
	public void onDeletedEmployee(DeletedEmployeeEvent event) {
		//DEBUG
		System.out.println("onDeletedEmployee");
	}

	
	@Override
	public void onCancel() {
		close();
	}
	
	private void close() {
		view.close();
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

	public void setView(AddEmployeeView view) {
		this.view = view;
	}

}
