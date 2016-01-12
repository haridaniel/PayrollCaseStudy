package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table;

import java.util.Optional;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.EmployeeCountChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeesTableView.EmployeesTableViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.EmployeeListUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.EmployeeListUseCase.ListEmployeesUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.Request;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.EmployeeListResponse;

public class EmployeesTableController implements EmployeesTableViewListener {
	
	private EmployeesTableView view;
	private ListEmployeesUseCaseFactory useCaseFactory;
	private EmployeesTablePresenter employeesTablePresenter = new EmployeesTablePresenter();
	private EventBus eventBus;

	public EmployeesTableController(
			EmployeesTableView view, 
			ListEmployeesUseCaseFactory useCaseFactory, 
			EventBus eventBus
			) {
		this.view = view;
		this.useCaseFactory = useCaseFactory;
		this.eventBus = eventBus;
		eventBus.register(this);
	}

	@Override
	public void onInitialized() {
		update();
	}
	
	@Subscribe
	public void onEmployeeCountChanged(EmployeeCountChangedEvent e) {
		update();
	}
	
	@Override
	public void onSelectionChanged(Optional<Integer> employeeId) {
		eventBus.post(new EmployeesTableSelectionChangedEvent(employeeId));
	}
	
	private void update() {
		view.setModel(employeesTablePresenter.toViewModel(employeeListUseCase()));
	}

	private EmployeeListResponse employeeListUseCase() {
		EmployeeListUseCase useCase = useCaseFactory.employeeListUseCase();
		useCase.execute(Request.EMPTY_REQUEST);
		return useCase.getResponse();
	}
	
}
