package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.table;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events.EmployeeChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.AbstractController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.Observable;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.Observable.ChangeListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.ObservableSelectedEployee;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.table.EmployeeListPresenter.EmployeeListPresenterFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.table.EmployeeListView.EmployeeListViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.EmployeeListUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.EmployeeListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.EmployeeListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.EmployeeListResponse.EmployeeForEmployeeListResponse;

public class EmployeeListController extends
	AbstractController<EmployeeListView, EmployeeListViewListener> implements 
	EmployeeListViewListener,
	ChangeListener<LocalDate>
{
	
	private EmployeeListUseCaseFactory useCaseFactory;
	private EmployeeListPresenterFactory employeeListPresenterFactory;
	
	private Observable<LocalDate> observableCurrentDate;
	private List<EmployeeForEmployeeListResponse> employees;
	private ObservableSelectedEployeeValue observableSelectedEployee = new ObservableSelectedEployeeValue();

	@Inject
	public EmployeeListController(
			EmployeeListUseCaseFactory useCaseFactory, 
			EventBus eventBus,
			EmployeeListPresenterFactory employeeListPresenterFactory
			) {
		this.useCaseFactory = useCaseFactory;
		this.employeeListPresenterFactory = employeeListPresenterFactory;
		eventBus.register(this);
	}
	
	public void setObservableCurrentDate(Observable<LocalDate> observableCurrentDate) {
		this.observableCurrentDate = observableCurrentDate;
		observableCurrentDate.addChangeListener(this);
	}

	public ObservableSelectedEployee getObservableSelectedEployee() {
		return observableSelectedEployee;
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
	public void onSelectionChanged(Optional<Integer> employeeIndex) {
		observableSelectedEployee.set(employeeIndex.map((i) -> employees.get(i)));
	}
	
	private void update() {
		EmployeeListResponse employeeListResponse = useCaseFactory.employeeListUseCase().execute(new EmployeeListRequest(observableCurrentDate.get()));
		employees = employeeListResponse.employees;
		getView().setModel(employeeListPresenterFactory.of(observableCurrentDate.get(), employeeListResponse).toViewModel());
	}

	private static class ObservableSelectedEployeeValue extends ObservableValue<Optional<EmployeeForEmployeeListResponse>> implements ObservableSelectedEployee {
		public ObservableSelectedEployeeValue() {
			super(Optional.empty());
		}
	}
	
	@Override
	protected EmployeeListViewListener getViewListener() {
		return this;
	}
}
