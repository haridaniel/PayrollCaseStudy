package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.ViewLoader;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.MainFrameController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.MainFrameWindow;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.dialog.uncaugthexception.UncaugthExceptionController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.dialog.uncaugthexception.UncaugthExceptionDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.dialog.uncaugthexception.UncaugthExceptionDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.EmployeeManagerController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.EmployeeManagerPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.EmployeeManagerPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.dialog.AddEmployeeController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.dialog.AddEmployeeDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.dialog.AddEmployeeView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.table.EmployeesTableController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.table.EmployeesTablePanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.statusbar.StatusBarController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.statusbar.StatusBarPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.statusbar.StatusBarView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.statusbar.component.StatusBarTextPane;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.usecasefactory.UseCaseFactory;

public class SwingViewFactory {

	private UseCaseFactory useCaseFactory;
	private EventBus eventBus;
	private ViewLoader viewLoader;

	public SwingViewFactory(UseCaseFactory useCaseFactory, EventBus eventBus, ViewLoader viewLoader) {
		this.useCaseFactory = useCaseFactory;
		this.eventBus = eventBus;
		this.viewLoader = viewLoader;
	}

	public MainFrameWindow mainFrameWindow() {
		MainFrameWindow mainFrameWindow = new MainFrameWindow(this);
		MainFrameController controller = new MainFrameController(mainFrameWindow, viewLoader);
		mainFrameWindow.setListener(controller);
		return mainFrameWindow;
	}

	public StatusBarPanel statusBarPanel() {
		StatusBarPanel statusBarPanel = new StatusBarPanel();
		StatusBarController controller = new StatusBarController(statusBarPanel, eventBus);
		return statusBarPanel;
	}

	public EmployeeManagerPanel employeeManagerPanel() {
		EmployeeManagerPanel view = new EmployeeManagerPanel(this);
		EmployeeManagerController controller = new EmployeeManagerController(view, useCaseFactory, eventBus);
		view.setListener(controller);
		return view;
	}
	public EmployeesTablePanel employeesTablePanel() {
		EmployeesTablePanel view = new EmployeesTablePanel();
		EmployeesTableController controller = new EmployeesTableController(view, useCaseFactory, eventBus);
		view.setListener(controller);
		return view;
	}
	
	public AddEmployeeDialog addEmployeeDialog() {
		AddEmployeeDialog dialog = new AddEmployeeDialog();
		AddEmployeeController controller = new AddEmployeeController(dialog, useCaseFactory, eventBus);
		dialog.setListener(controller);
		return dialog;
	}

	public UncaugthExceptionDialog uncaugthExceptionDialog(Throwable e) {
		UncaugthExceptionDialog dialog = new UncaugthExceptionDialog();
		UncaugthExceptionController controller = new UncaugthExceptionController(dialog, e);
		dialog.setListener(controller);
		return dialog;
	}

	
	
}
