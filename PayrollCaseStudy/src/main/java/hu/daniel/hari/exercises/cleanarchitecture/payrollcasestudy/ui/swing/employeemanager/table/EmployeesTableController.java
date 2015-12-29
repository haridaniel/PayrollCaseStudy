package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.employeemanager.table;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.employeemanager.EmployeeCountChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.employeemanager.table.EmployeesTableView.EmployeesOverviewPanelListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.employeemanager.table.EmployeesTableViewModel.EmployeeViewItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.EmployeesOverviewUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.EmployeesOverviewUseCase.EmployeesOverviewUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.Request.EmptyRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.EmployeesOverviewUseCaseResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.EmployeesOverviewUseCaseResponse.EmployeeItem;

public class EmployeesTableController implements EmployeesOverviewPanelListener {
	
	private EmployeesTableView view;
	private EmployeesOverviewUseCaseFactory useCaseFactory;
	private EmployeesOverviewPanelPresenter presenter;
	private EventBus eventBus;

	public EmployeesTableController(EmployeesTableView view, EmployeesOverviewUseCaseFactory useCaseFactory, EventBus eventBus) {
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
		EmployeesOverviewUseCase useCase = useCaseFactory.employeesOverviewUseCase();
		useCase.execute(new EmptyRequest());
		view.setModel(presenter.present(useCase.getResponse()));
	}

	private static class EmployeesOverviewPanelPresenter {
		
		public EmployeesTableViewModel present(EmployeesOverviewUseCaseResponse response) {
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
