package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager;

import java.util.Optional;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.DeletedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerView.EmployeeManagerViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeesTableSelectionChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.DeleteEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.DeleteEmployeeUseCase.DeleteEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase.GetEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.GetEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeItem;

public class EmployeeManagerController implements EmployeeManagerViewListener {

	private EmployeeManagerView view;
	private DeleteEmployeeUseCaseFactory deleteEmployeeUseCaseFactory;
	private GetEmployeeUseCaseFactory getEmployeeUseCaseFactory;
	private EventBus eventBus;
	
	private Optional<Integer> currentSelectedEmployeeId;

	public EmployeeManagerController(
			EmployeeManagerView view, 
			DeleteEmployeeUseCaseFactory deleteEmployeeUseCaseFactory, 
			GetEmployeeUseCaseFactory getEmployeeUseCaseFactory, 
			EventBus eventBus) {
		this.view = view;
		this.deleteEmployeeUseCaseFactory = deleteEmployeeUseCaseFactory;
		this.getEmployeeUseCaseFactory = getEmployeeUseCaseFactory;
		this.eventBus = eventBus;
		eventBus.register(this);
	}

	@Subscribe
	public void onEmployeeSelectionChanged(EmployeesTableSelectionChangedEvent e) {
		currentSelectedEmployeeId = e.employeeId;
		updateView();
	}

	private void updateView() {
		updateButtonsEnabled();
	}

	private void updateButtonsEnabled() {
		view.setButtonsEnabled(currentSelectedEmployeeId.isPresent());
	}

	@Override
	public void onDeleteAction() {
		EmployeeItem employeeItemToBeDeleted = getEmployeeItem(currentSelectedEmployeeId.get());
		deleteEmployeeUseCaseFactory.deleteEmployeeUseCase().execute(new DeleteEmployeeRequest(currentSelectedEmployeeId.get()));
		eventBus.post(new DeletedEmployeeEvent(employeeItemToBeDeleted.id, employeeItemToBeDeleted.name));
	}

	private EmployeeItem getEmployeeItem(int employeeId) {
		GetEmployeeUseCase getEmployeeUseCase = getEmployeeUseCaseFactory.getEmployeeUseCase();
		getEmployeeUseCase.execute(new GetEmployeeRequest(employeeId));
		return getEmployeeUseCase.getResponse().employeeItem;
	}

}
