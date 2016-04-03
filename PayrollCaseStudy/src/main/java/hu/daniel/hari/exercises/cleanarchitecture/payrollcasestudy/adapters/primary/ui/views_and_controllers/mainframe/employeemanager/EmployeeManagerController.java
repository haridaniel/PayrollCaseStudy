package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager;

import java.util.Optional;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.DeletedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ViewLoader;
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
	private ViewLoader viewLoader;
	private EmployeeManagerControllerListener controllerListener;

	@Inject
	public EmployeeManagerController(
			DeleteEmployeeUseCaseFactory deleteEmployeeUseCaseFactory, 
			GetEmployeeUseCaseFactory getEmployeeUseCaseFactory, 
			EventBus eventBus
			) {
		this.deleteEmployeeUseCaseFactory = deleteEmployeeUseCaseFactory;
		this.getEmployeeUseCaseFactory = getEmployeeUseCaseFactory;
		this.eventBus = eventBus;
		eventBus.register(this);
		viewLoader = null;
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
		controllerListener.openAddEmployeeDialog();
//		viewLoader.loadAddEmployeeDialogView();
	}
	
	public void setControllerListener(EmployeeManagerControllerListener controllerListener) {
		this.controllerListener = controllerListener;
	}
	
	public static interface EmployeeManagerControllerListener {
		void openAddEmployeeDialog();
	}

}
