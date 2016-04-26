package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.table;

import java.time.LocalDate;
import java.util.Optional;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.globalevents.EmployeeChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.AbstractController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.Controller;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ObservableValueImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ObservableValue.ChangeListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.table.EmployeeListView.EmployeeListViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.table.EmployeeListView.EmployeeListViewModel.EmployeeViewItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.EmployeeListUseCase;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.employeelist.EmployeeListUseCase.ListEmployeesUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.request.EmployeeListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.EmployeeListResponse;

public class EmployeeListController extends
	AbstractController<EmployeeListView, EmployeeListViewListener> implements 
	EmployeeListViewListener,
	ChangeListener<LocalDate>
{
	
	private ListEmployeesUseCaseFactory useCaseFactory;
	private ObservableValue<LocalDate> observableCurrentDate;
	private ObservableSelectedEployeeViewItemImpl observableSelectedEployeeViewItem = new ObservableSelectedEployeeViewItemImpl();

	@Inject
	public EmployeeListController(
			ListEmployeesUseCaseFactory useCaseFactory, 
			EventBus eventBus
			) {
		this.useCaseFactory = useCaseFactory;
		eventBus.register(this);
	}
	
	public void setObservableCurrentDate(ObservableValue<LocalDate> observableCurrentDate) {
		this.observableCurrentDate = observableCurrentDate;
		observableCurrentDate.addChangeListener(this);
	}

	public ObservableSelectedEployeeItem getObservableSelectedEployeeId() {
		return observableSelectedEployeeViewItem;
	}

	@Subscribe
	public void onEmployeeChanged(EmployeeChangedEvent e) {
		update();
	}
	
	@Override
	public void onChanged(LocalDate currentDate) {
		update();
	}

	@Override
	public void onSelectionChanged(Optional<EmployeeViewItem> employee) {
		observableSelectedEployeeViewItem.set(employee);
	}
	
	private void update() {
		getView().setModel(new EmployeeListPresenter(observableCurrentDate.get(), executeEmployeeListUseCase()).toViewModel());
	}

	private EmployeeListResponse executeEmployeeListUseCase() {
		EmployeeListUseCase useCase = useCaseFactory.employeeListUseCase();
		useCase.execute(new EmployeeListRequest(observableCurrentDate.get()));
		return useCase.getResponse();
	}

	private static class ObservableSelectedEployeeViewItemImpl extends ObservableValueImpl<Optional<EmployeeViewItem>> implements ObservableSelectedEployeeItem {
		public ObservableSelectedEployeeViewItemImpl() {
			super(Optional.empty());
		}
	}

	@Override
	protected EmployeeListViewListener getViewListener() {
		return this;
	}
}
