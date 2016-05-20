package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.table;

import java.time.LocalDate;
import java.util.Optional;

import javax.inject.Inject;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.events.EmployeeChangedEvent;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.AbstractController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.Observable;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.Observable.ChangeListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.EmployeeViewItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.ObservableSelectedEployeeItem;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.table.EmployeeListView.EmployeeListViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.factories.EmployeeListUseCaseFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.EmployeeListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.EmployeeListResponse;

public class EmployeeListController extends
	AbstractController<EmployeeListView, EmployeeListViewListener> implements 
	EmployeeListViewListener,
	ChangeListener<LocalDate>
{
	
	private EmployeeListUseCaseFactory useCaseFactory;
	private Observable<LocalDate> observableCurrentDate;
	private ObservableSelectedEployeeViewItemImpl observableSelectedEployeeViewItem = new ObservableSelectedEployeeViewItemImpl();

	@Inject
	public EmployeeListController(
			EmployeeListUseCaseFactory useCaseFactory, 
			EventBus eventBus
			) {
		this.useCaseFactory = useCaseFactory;
		eventBus.register(this);
	}
	
	public void setObservableCurrentDate(Observable<LocalDate> observableCurrentDate) {
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
		return useCaseFactory.employeeListUseCase().execute(new EmployeeListRequest(observableCurrentDate.get()));
	}

	private static class ObservableSelectedEployeeViewItemImpl extends ObservableValue<Optional<EmployeeViewItem>> implements ObservableSelectedEployeeItem {
		public ObservableSelectedEployeeViewItemImpl() {
			super(Optional.empty());
		}
	}

	@Override
	protected EmployeeListViewListener getViewListener() {
		return this;
	}
}
