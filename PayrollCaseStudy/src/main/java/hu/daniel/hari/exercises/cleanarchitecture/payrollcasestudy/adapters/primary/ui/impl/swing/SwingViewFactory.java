package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing;

import javax.swing.JFrame;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.AddEmployeeDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.EmployeeManagerPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.EmployeesTablePanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.MainFrameWindow;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.StatusBarPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.UncaugthExceptionDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.StatusBarTextPane;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ViewLoader;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.dialog.uncaugthexception.UncaugthExceptionController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainFrameController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.EmployeeManagerController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.dialog.AddEmployeeController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.dialog.AddEmployeeView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.employeemanager.table.EmployeesTableController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.statusbar.StatusBarController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.statusbar.StatusBarView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.statusbar.messageformatter.StatusBarMessageFormatter;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.UseCaseFactory;

public class SwingViewFactory {

	private UseCaseFactory useCaseFactory;
	private EventBus eventBus;
	private ViewLoader viewLoader;
	private JFrame mainFrame = null; //Nullable

	public SwingViewFactory(UseCaseFactory useCaseFactory, EventBus eventBus, ViewLoader viewLoader) {
		this.useCaseFactory = useCaseFactory;
		this.eventBus = eventBus;
		this.viewLoader = viewLoader;
	}

	public MainFrameWindow mainFrameWindow() {
		MainFrameWindow mainFrameWindow = new MainFrameWindow(this);
		MainFrameController controller = new MainFrameController(mainFrameWindow, viewLoader);
		mainFrameWindow.setListener(controller);
		setMainFrame(mainFrameWindow);
		return mainFrameWindow;
	}

	private void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public StatusBarPanel statusBarPanel() {
		StatusBarPanel statusBarPanel = new StatusBarPanel();
		StatusBarController controller = new StatusBarController(statusBarPanel, eventBus, new StatusBarMessageFormatter());
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
		AddEmployeeDialog dialog = new AddEmployeeDialog(mainFrame);
		AddEmployeeController controller = new AddEmployeeController(dialog, useCaseFactory, eventBus);
		dialog.setListener(controller);
		return dialog;
	}

	public UncaugthExceptionDialog uncaugthExceptionDialog(Throwable e) {
		UncaugthExceptionDialog dialog = new UncaugthExceptionDialog(mainFrame);
		UncaugthExceptionController controller = new UncaugthExceptionController(dialog, e);
		dialog.setListener(controller);
		return dialog;
	}

	
	
}
