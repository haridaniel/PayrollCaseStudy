package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.msg.confirm.ConfirmMessageFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.DeletedEmployeeEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.AbstractController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addemployee.AddEmployeeUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.addtimecard.AddTimeCardUI.AddTimeCardUIFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.common.confirm.ConfirmDialogUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.common.confirm.ConfirmDialogUI.ConfirmDialogListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.EmployeeManagerView.EmployeeManagerViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.EmployeeManagerView.EmployeeManagerViewModel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.EmployeeManagerView.EmployeeManagerViewModel.ButtonEnabledStates;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.table.ObservableSelectedEployeeItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.table.EmployeeListView.EmployeeListViewModel.EmployeeViewItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.table.EmployeeListView.EmployeeListViewModel.EmployeeViewItem.PaymentType.PaymentTypeVisitor;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.DeleteEmployeeUseCase.DeleteEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.find.GetEmployeeUseCase.GetEmployeeUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.DeleteEmployeeRequest;

public class EmployeeManagerController extends 
	AbstractController<EmployeeManagerView, EmployeeManagerViewListener> implements
	EmployeeManagerViewListener 
{

	private DeleteEmployeeUseCaseFactory deleteEmployeeUseCaseFactory;
	private EventBus eventBus;
	
	private Provider<AddEmployeeUI<?>> addEmployeeUIProvider;
	private Provider<ConfirmDialogUI> confirmDialogUIProvider;
	private AddTimeCardUIFactory addTimeCardUIFactory;
	private ObservableSelectedEployeeItem observableSelectedEployeeItem;
	private ConfirmMessageFormatter confirmMessageFormatter;

	@Inject
	public EmployeeManagerController(
			DeleteEmployeeUseCaseFactory deleteEmployeeUseCaseFactory, 
			GetEmployeeUseCaseFactory getEmployeeUseCaseFactory, 
			EventBus eventBus,
			Provider<AddEmployeeUI<?>> addEmployeeUIProvider,
			Provider<ConfirmDialogUI> confirmDialogUIProvider,
			AddTimeCardUIFactory addTimeCardUIFactory,
			ConfirmMessageFormatter confirmMessageFormatter
			) {
		this.deleteEmployeeUseCaseFactory = deleteEmployeeUseCaseFactory;
		this.eventBus = eventBus;
		this.addEmployeeUIProvider = addEmployeeUIProvider;
		this.confirmDialogUIProvider = confirmDialogUIProvider;
		this.addTimeCardUIFactory = addTimeCardUIFactory;
		this.confirmMessageFormatter = confirmMessageFormatter;
	}

	public void setObservableSelectedEployeeId(ObservableSelectedEployeeItem observableSelectedEployeeItem) {
		this.observableSelectedEployeeItem = observableSelectedEployeeItem;
		observableSelectedEployeeItem.addChangeListener(newValue -> {
			onSelectedEmployeeIdChanged();
		});
	}

	@Override
	protected EmployeeManagerViewListener getViewListener() {
		return this;
	}

	private void onSelectedEmployeeIdChanged() {
		updateView();
	}

	private void updateView() {
		view.setModel(new EmployeeManagerViewPresenter().present(observableSelectedEployeeItem.get()));
	}

	@Override
	public void onDeleteEmployeeAction() {
		EmployeeViewItem employeeItem = observableSelectedEployeeItem.get().get();
		confirmDialogUIProvider.get().show(confirmMessageFormatter.deleteEmployee(employeeItem.name), new ConfirmDialogListener() {
			@Override
			public void onOk() {
				deleteEmployeeUseCaseFactory.deleteEmployeeUseCase().execute(new DeleteEmployeeRequest(employeeItem.id));
				eventBus.post(new DeletedEmployeeEvent(employeeItem.id, employeeItem.name));
			}
		});
	}

	@Override
	public void onAddEmployeeAction() {
		addEmployeeUIProvider.get().show();
	}
	
	@Override
	public void onAddTimeCardAction() {
		addTimeCardUIFactory.create(observableSelectedEployeeItem.get().get().id).show();
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
