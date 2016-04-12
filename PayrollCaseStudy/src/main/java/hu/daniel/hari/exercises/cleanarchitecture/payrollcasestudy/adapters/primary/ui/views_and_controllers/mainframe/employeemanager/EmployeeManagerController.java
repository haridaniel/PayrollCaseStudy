package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.DeletedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.dialog.AddEmployeeDialogUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerView.EmployeeManagerViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.ObservableSelectedEployeeId;
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
	
	private Provider<AddEmployeeDialogUI> addEmployeeDialogUIProvider;
	private ObservableSelectedEployeeId observableSelectedEployeeId;

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
	}

	public void setView(EmployeeManagerView view) {
		this.view = view;
	}
	
	public void setObservableSelectedEployeeId(ObservableSelectedEployeeId observableSelectedEployeeId) {
		this.observableSelectedEployeeId = observableSelectedEployeeId;
		observableSelectedEployeeId.addChangeListener(newValue -> {
			onSelectedEmployeeIdChanged();
		});
	}

	private void onSelectedEmployeeIdChanged() {
		updateView();
	}

	private void updateView() {
		updateSelectionBasedButtonsEnabled();
	}

	private void updateSelectionBasedButtonsEnabled() {
		view.setButtonsEnabled(observableSelectedEployeeId.get().isPresent());
	}

	@Override
	public void onDeleteAction() {
		EmployeeForGetEmployeeResponse employeeItemToBeDeleted = getEmployeeItem(observableSelectedEployeeId.get().get());
		deleteEmployeeUseCaseFactory.deleteEmployeeUseCase().execute(new DeleteEmployeeRequest(observableSelectedEployeeId.get().get()));
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
