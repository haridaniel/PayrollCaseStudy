package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.employeemanager;

import java.util.Optional;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.employeemanager.EmployeeManagerView.EmployeesManagerViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.employeemanager.table.EmployeeSelectionChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.usecasefactory.UseCaseFactory;

public class EmployeeManagerController implements EmployeesManagerViewListener {

	private EmployeeManagerView view;
	private UseCaseFactory useCaseFactory;
	private EventBus eventBus;
	
	private Optional<Integer> selectedEmployeeId;

	public EmployeeManagerController(EmployeeManagerView view, UseCaseFactory useCaseFactory, EventBus eventBus) {
		this.view = view;
		this.useCaseFactory = useCaseFactory;
		this.eventBus = eventBus;
		view.setListener(this);
		eventBus.register(this);
	}

	@Subscribe
	public void onEmployeeSelectionChanged(EmployeeSelectionChangedEvent e) {
		System.out.println("onSelectionChanged : " + e.employeeId);
		selectedEmployeeId = e.employeeId;
		updateView();
	}

	private void updateView() {
		updateButtonsEnabled();
	}

	private void updateButtonsEnabled() {
		if(selectedEmployeeId.isPresent())
			view.enableButtons();
		else
			view.disableButtons();
	}

	@Override
	public void onDeleteAction() {
		System.out.println("onDeleteAction id=" + selectedEmployeeId);
		useCaseFactory.deleteEmployeeUseCase().execute(new DeleteEmployeeRequest(selectedEmployeeId.get()));
		eventBus.post(new EmployeeCountChangedEvent());
	}

}
