package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.employeemanager;

import java.util.Optional;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.globalevents.DeletedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.globalevents.EmployeeCountChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.employeemanager.EmployeeManagerView.EmployeesManagerViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.employeemanager.table.EmployeeSelectionChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.find.GetEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.find.ListEmployeesUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.GetEmployeeUseCaseRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.Request.EmptyRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.EmployeeItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.GetEmployeeUseCaseResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.ListEmployeesUseCaseResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.usecasefactory.UseCaseFactory;

public class EmployeeManagerController implements EmployeesManagerViewListener {

	private EmployeeManagerView view;
	private UseCaseFactory useCaseFactory;
	private EventBus eventBus;
	
	private Optional<Integer> currentSelectedEmployeeId;

	public EmployeeManagerController(EmployeeManagerView view, UseCaseFactory useCaseFactory, EventBus eventBus) {
		this.view = view;
		this.useCaseFactory = useCaseFactory;
		this.eventBus = eventBus;
		view.setListener(this);
		eventBus.register(this);
	}

	@Subscribe
	public void onEmployeeSelectionChanged(EmployeeSelectionChangedEvent e) {
		currentSelectedEmployeeId = e.employeeId;
		updateView();
	}

	private void updateView() {
		updateButtonsEnabled();
	}

	private void updateButtonsEnabled() {
		if(currentSelectedEmployeeId.isPresent())
			view.enableButtons();
		else
			view.disableButtons();
	}

	@Override
	public void onDeleteAction() {
		EmployeeItem employeeItemToBeDeleted = getEmployeeItem(currentSelectedEmployeeId.get());
		useCaseFactory.deleteEmployeeUseCase().execute(new DeleteEmployeeRequest(currentSelectedEmployeeId.get()));
		eventBus.post(new DeletedEmployeeEvent(employeeItemToBeDeleted.id, employeeItemToBeDeleted.name));
	}

	private EmployeeItem getEmployeeItem(int employeeId) {
		GetEmployeeUseCase getEmployeeUseCase = useCaseFactory.getEmployeeUseCase();
		getEmployeeUseCase.execute(new GetEmployeeUseCaseRequest(employeeId));
		return getEmployeeUseCase.getResponse().employeeItem;
	}

}
