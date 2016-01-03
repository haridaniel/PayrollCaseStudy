package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.table;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.globalevents.EmployeeCountChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.table.EmployeesTableView.EmployeesTableViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.table.EmployeesTableView.EmployeesTableViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.table.EmployeesTableView.EmployeesTableViewModel.EmployeeViewItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.find.ListEmployeesUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase.find.ListEmployeesUseCase.ListEmployeesUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.Request.EmptyRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.EmployeeItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.response.ListEmployeesUseCaseResponse;

public class EmployeesTableController implements EmployeesTableViewListener {
	
	private EmployeesTableView view;
	private ListEmployeesUseCaseFactory useCaseFactory;
	private ModelConverter modelConverter;
	private EventBus eventBus;

	public EmployeesTableController(EmployeesTableView view, ListEmployeesUseCaseFactory useCaseFactory, EventBus eventBus) {
		this.view = view;
		this.useCaseFactory = useCaseFactory;
		this.eventBus = eventBus;
		this.modelConverter = new ModelConverter();
		eventBus.register(this);
	}

	@Override
	public void onLoaded() {
		update();
	}
	
	@Subscribe
	public void onEmployeeCountChanged(EmployeeCountChangedEvent e) {
		update();
	}
	
	private void update() {
		ListEmployeesUseCase useCase = useCaseFactory.listEmployeesUseCase();
		useCase.execute(new EmptyRequest());
		view.setModel(modelConverter.toViewModel(useCase.getResponse()));
	}

	@Override
	public void onSelectionChanged(Optional<Integer> employeeId) {
		eventBus.post(new EmployeesTableSelectionChangedEvent(employeeId));
	}

	private static class ModelConverter {
		
		public EmployeesTableViewModel toViewModel(ListEmployeesUseCaseResponse response) {
			return new EmployeesTableViewModel(toViewModel(response.employeeItems));
		}
		
		private List<EmployeeViewItem> toViewModel(List<EmployeeItem> employeeItems) {
			return employeeItems.stream()
				.map(employeeItem -> toViewModel(employeeItem))
				.collect(Collectors.toList());
		}

		private EmployeeViewItem toViewModel(EmployeeItem employeeItem) {
			EmployeeViewItem employeeViewItem = new EmployeeViewItem();
			employeeViewItem.id = employeeItem.id;
			employeeViewItem.name = employeeItem.name;
			employeeViewItem.address = employeeItem.address;
			employeeViewItem.paymentClassificationType = employeeItem.paymentClassificationType.name();
			return employeeViewItem;
		}

	}
	
}
