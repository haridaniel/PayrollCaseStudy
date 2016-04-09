package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.DeletedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.dialog.AddEmployeeDialogUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerView.EmployeeManagerViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeeListSelectionChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.DeleteEmployeeUseCase.DeleteEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase.GetEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.GetEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.GetEmployeeResponse.EmployeeForGetEmployeeResponse;

public class EmployeeManagerController implements EmployeeManagerViewListener {

	private EmployeeManagerView view;
	private DeleteEmployeeUseCaseFactory deleteEmployeeUseCaseFactory;
	private GetEmployeeUseCaseFactory getEmployeeUseCaseFactory;
	private EventBus eventBus;
	
	private Optional<Integer> currentSelectedEmployeeId;
	private Provider<AddEmployeeDialogUI> addEmployeeDialogUIProvider;

	@Inject
	public EmployeeManagerController(
			DeleteEmployeeUseCaseFactory deleteEmployeeUseCaseFactory, 
			GetEmployeeUseCaseFactory getEmployeeUseCaseFactory, 
			EventBus eventBus,
			Provider<AddEmployeeDialogUI> addEmployeeDialogUIProvider
			) {
		this.deleteEmployeeUseCaseFactory = deleteEmployeeUseCaseFactory;
		this.getEmployeeUseCaseFactory = getEmployeeUseCaseFactory;
		this.eventBus = eventBus;
		this.addEmployeeDialogUIProvider = addEmployeeDialogUIProvider;
		eventBus.register(this);
	}

	@Subscribe
	public void onEmployeeSelectionChanged(EmployeeListSelectionChangedEvent e) {
		currentSelectedEmployeeId = e.employeeId;
		updateView();
	}
	
	public void setView(EmployeeManagerView view) {
		this.view = view;
	}
	
	private void updateView() {
		updateButtonsEnabled();
	}

	private void updateButtonsEnabled() {
		view.setButtonsEnabled(currentSelectedEmployeeId.isPresent());
	}

	@Override
	public void onDeleteAction() {
		EmployeeForGetEmployeeResponse employeeItemToBeDeleted = getEmployeeItem(currentSelectedEmployeeId.get());
		deleteEmployeeUseCaseFactory.deleteEmployeeUseCase().execute(new DeleteEmployeeRequest(currentSelectedEmployeeId.get()));
		eventBus.post(new DeletedEmployeeEvent(employeeItemToBeDeleted.id, employeeItemToBeDeleted.name));
	}

	private EmployeeForGetEmployeeResponse getEmployeeItem(int employeeId) {
		GetEmployeeUseCase getEmployeeUseCase = getEmployeeUseCaseFactory.getEmployeeUseCase();
		getEmployeeUseCase.execute(new GetEmployeeRequest(employeeId));
		return getEmployeeUseCase.getResponse().employeeForGetEmployeeResponse;
	}

	@Override
	public void onAddAction() {
		addEmployeeDialogUIProvider.get().show();
	}

}
