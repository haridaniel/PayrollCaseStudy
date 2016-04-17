package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.DeletedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.dialog.AddEmployeeDialogUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.dialog.AddTimeCardDialogUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.dialog.AddTimeCardDialogUI.AddTimeCardDialogUIFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.dialog.addtimecard.AddTimeCardDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerView.EmployeeManagerViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerView.EmployeeManagerViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerView.EmployeeManagerViewModel.ButtonEnabledStates;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeeListView.EmployeeListViewModel.EmployeeViewItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeeListView.EmployeeListViewModel.EmployeeViewItem.PaymentType.PaymentTypeVisitor;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.ObservableSelectedEployeeItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.DeleteEmployeeUseCase.DeleteEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase.GetEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.DeleteEmployeeRequest;

public class EmployeeManagerController implements EmployeeManagerViewListener {

	private EmployeeManagerView view;
	private DeleteEmployeeUseCaseFactory deleteEmployeeUseCaseFactory;
	private EventBus eventBus;
	
	private Provider<AddEmployeeDialogUI> addEmployeeDialogUIProvider;
	private AddTimeCardDialogUIFactory addTimeCardDialogUIFactory;
	private ObservableSelectedEployeeItem observableSelectedEployeeItem;

	@Inject
	public EmployeeManagerController(
			DeleteEmployeeUseCaseFactory deleteEmployeeUseCaseFactory, 
			GetEmployeeUseCaseFactory getEmployeeUseCaseFactory, 
			EventBus eventBus,
			Provider<AddEmployeeDialogUI> addEmployeeDialogUIProvider,
			AddTimeCardDialogUIFactory addTimeCardDialogUIFactory
			) {
		this.deleteEmployeeUseCaseFactory = deleteEmployeeUseCaseFactory;
		this.eventBus = eventBus;
		this.addEmployeeDialogUIProvider = addEmployeeDialogUIProvider;
		this.addTimeCardDialogUIFactory = addTimeCardDialogUIFactory;
	}

	public void setView(EmployeeManagerView view) {
		this.view = view;
	}
	
	public void setObservableSelectedEployeeId(ObservableSelectedEployeeItem observableSelectedEployeeItem) {
		this.observableSelectedEployeeItem = observableSelectedEployeeItem;
		observableSelectedEployeeItem.addChangeListener(newValue -> {
			onSelectedEmployeeIdChanged();
		});
	}

	private void onSelectedEmployeeIdChanged() {
		updateView();
	}

	private void updateView() {
		view.setModel(new EmployeeManagerViewPresenter().present(observableSelectedEployeeItem.get()));
	}

	@Override
	public void onDeleteEmployeeAction() {
		EmployeeViewItem employeeViewItem = observableSelectedEployeeItem.get().get();
		deleteEmployeeUseCaseFactory.deleteEmployeeUseCase().execute(new DeleteEmployeeRequest(employeeViewItem.id));
		eventBus.post(new DeletedEmployeeEvent(employeeViewItem.id, employeeViewItem.name));
	}

	@Override
	public void onAddEmployeeAction() {
		addEmployeeDialogUIProvider.get().show();
	}
	
	@Override
	public void onAddTimeCardAction() {
		addTimeCardDialogUIFactory.create(observableSelectedEployeeItem.get().get().id).show();
	}

	private static class EmployeeManagerViewPresenter {
		public EmployeeManagerViewModel present(Optional<EmployeeViewItem> selectedEmployeeViewItem) {
			return new EmployeeManagerViewModel(presentButtonsEnabledStates(selectedEmployeeViewItem));
		}
	
		private ButtonEnabledStates presentButtonsEnabledStates(Optional<EmployeeViewItem> selectedEmployeeViewItem) {
			ButtonEnabledStates buttonsEnabledStates = new ButtonEnabledStates();
			buttonsEnabledStates.deleteEmployee = selectedEmployeeViewItem.isPresent();
			selectedEmployeeViewItem.ifPresent((employeeItem) -> {
				presentButtonsEnabledStatesForSelectedEmployee(buttonsEnabledStates, employeeItem);
			});
			return buttonsEnabledStates;
		}

		private void presentButtonsEnabledStatesForSelectedEmployee(ButtonEnabledStates buttonsEnabledStates, EmployeeViewItem employeeItem) {
			employeeItem.paymentType.accept(new PaymentTypeVisitor() {
				@Override
				public void visitCommissioned() {
					buttonsEnabledStates.addSalesReceipt = true;
				}
				@Override
				public void visitHourly() {
					buttonsEnabledStates.addTimeCard = true;
				}
				@Override
				public void visitSalaried() {
				}
			});
		}
		
	}


}
