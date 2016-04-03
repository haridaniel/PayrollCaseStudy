package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table;

import java.util.Optional;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.EmployeeCountChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeeListView.EmployeeListViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.EmployeeListUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.EmployeeListUseCase.ListEmployeesUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.Request;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeListResponse;

public class EmployeeListController implements EmployeeListViewListener {
	
	private EmployeeListView view;
	private ListEmployeesUseCaseFactory useCaseFactory;
	private EmployeeListPresenter employeeListPresenter = new EmployeeListPresenter();
	private EventBus eventBus;

	@Inject
	public EmployeeListController(
			ListEmployeesUseCaseFactory useCaseFactory, 
			EventBus eventBus
			) {
		this.useCaseFactory = useCaseFactory;
		this.eventBus = eventBus;
		eventBus.register(this);
	}
	
	public void setView(EmployeeListView view) {
		this.view = view;
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
		eventBus.post(new EmployeeListSelectionChangedEvent(employeeId));
	}
	
	private void update() {
		view.setModel(employeeListPresenter.toViewModel(employeeListUseCase()));
	}

	private EmployeeListResponse employeeListUseCase() {
		EmployeeListUseCase useCase = useCaseFactory.employeeListUseCase();
		useCase.execute(Request.EMPTY_REQUEST);
		return useCase.getResponse();
	}
	
}
