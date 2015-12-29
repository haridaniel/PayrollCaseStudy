package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.viewscontrollers.employeemanager.table;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.events.EmployeeCountChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.viewscontrollers.employeemanager.table.EmployeesTableView.EmployeesOverviewPanelListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.viewscontrollers.employeemanager.table.EmployeesTableViewModel.EmployeeViewItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.find.ListEmployeesUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.find.ListEmployeesUseCase.ListEmployeesUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.Request.EmptyRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.EmployeeItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.ListEmployeesUseCaseResponse;

public class EmployeesTableController implements EmployeesOverviewPanelListener {
	
	private EmployeesTableView view;
	private ListEmployeesUseCaseFactory useCaseFactory;
	private EmployeesOverviewPanelPresenter presenter;
	private EventBus eventBus;

	public EmployeesTableController(EmployeesTableView view, ListEmployeesUseCaseFactory useCaseFactory, EventBus eventBus) {
		this.view = view;
		this.eventBus = eventBus;
		this.presenter = new EmployeesOverviewPanelPresenter();
		this.useCaseFactory = useCaseFactory;
		eventBus.register(this);
		view.setListener(this);
	}

	@Override
	public void onViewAdded() {
		updateTable();
	}
	
	@Subscribe
	public void onEmployeeCountChanged(EmployeeCountChangedEvent e) {
		updateTable();
	}
	
	private void updateTable() {
		ListEmployeesUseCase useCase = useCaseFactory.listEmployeesUseCase();
		useCase.execute(new EmptyRequest());
		view.setModel(presenter.present(useCase.getResponse()));
	}

	private static class EmployeesOverviewPanelPresenter {
		
		public EmployeesTableViewModel present(ListEmployeesUseCaseResponse response) {
			return new EmployeesTableViewModel(present(response.employeeItems));
		}
		
		private List<EmployeeViewItem> present(List<EmployeeItem> employeeItems) {
			return employeeItems.stream()
				.map(employeeItem -> present(employeeItem))
				.collect(Collectors.toList());
		}

		private EmployeeViewItem present(EmployeeItem employeeItem) {
			EmployeeViewItem employeeViewItem = new EmployeeViewItem();
			employeeViewItem.id = employeeItem.id;
			employeeViewItem.name = employeeItem.name;
			employeeViewItem.address = employeeItem.address;
			employeeViewItem.paymentClassificationType = employeeItem.paymentClassificationType.name();
			return employeeViewItem;
		}

	}
	
}
