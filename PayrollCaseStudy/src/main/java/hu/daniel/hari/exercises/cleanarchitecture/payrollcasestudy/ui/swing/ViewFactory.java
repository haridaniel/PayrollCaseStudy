package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.employeemanager.EmployeeManagerController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.employeemanager.EmployeeManagerView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.employeemanager.table.EmployeesTableController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.employeemanager.table.EmployeesTableView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.usecasefactory.UseCaseFactory;

public class ViewFactory {

	private UseCaseFactory useCaseFactory;
	private EventBus eventBus;

	public ViewFactory(UseCaseFactory useCaseFactory) {
		this.useCaseFactory = useCaseFactory;
		eventBus = createEventBus();
	}
	
	private EventBus createEventBus() {
//		return new EventBus();
		return new EventQueueAsyncEventBus();
	}

	public EmployeeManagerView employeeManagerView() {
		EmployeeManagerView view = new EmployeeManagerView(this);
		new EmployeeManagerController(view, useCaseFactory, eventBus);
		return view;
	}
	public EmployeesTableView employeesTableView() {
		EmployeesTableView view = new EmployeesTableView(eventBus);
		new EmployeesTableController(view, useCaseFactory, eventBus);
		return view;
	}
	
	public MainFrameView mainFrameView() {
		MainFrameView mainFrameView = new MainFrameView(this);
		new MainFrameController(mainFrameView);
		return mainFrameView;
	}

	
	
}
