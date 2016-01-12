package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.date.NextPaydayDateFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.EmployeeCountChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeesTableView.EmployeesTableViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeesTableView.EmployeesTableViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeesTableView.EmployeesTableViewModel.EmployeeViewItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.EmployeeListUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.EmployeeListUseCase.ListEmployeesUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.Request;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeListResponse.EmployeeListItem;

public class EmployeesTableController implements EmployeesTableViewListener {
	
	private EmployeesTableView view;
	private ListEmployeesUseCaseFactory useCaseFactory;
	private Presenter presenter = new Presenter();
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
		EmployeeListUseCase useCase = useCaseFactory.employeeListUseCase();
		useCase.execute(Request.EMPTY_REQUEST);
		view.setModel(presenter.toViewModel(useCase.getResponse()));
	}

	private static class Presenter {
		
		private NextPaydayDateFormatter nextPaydayDateFormatter = new NextPaydayDateFormatter(LocalDate.now());
		
		public EmployeesTableViewModel toViewModel(EmployeeListResponse response) {
			return new EmployeesTableViewModel(toViewModel(response.employeeListItems));
		}
		
		private List<EmployeeViewItem> toViewModel(List<EmployeeListItem> employeeItems) {
			return employeeItems.stream()
				.map(employeeItem -> toViewModel(employeeItem))
				.collect(Collectors.toList());
		}

		private EmployeeViewItem toViewModel(EmployeeListItem employeeItem) {
			EmployeeViewItem employeeViewItem = new EmployeeViewItem();
			employeeViewItem.id = employeeItem.id;
			employeeViewItem.name = employeeItem.name;
			employeeViewItem.address = employeeItem.address;
			employeeViewItem.paymentTypeEnum = employeeItem.paymentTypeEnum.name();
			employeeViewItem.waging = employeeItem.paymentTypeString;
			employeeViewItem.nextPayDay = nextPaydayDateFormatter.format(employeeItem.nextPayDay);
			return employeeViewItem;
		}
		

	}
	
}
