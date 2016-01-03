package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.ViewLoader;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.SwingViewFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.MainFrameWindow;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.employeemanager.dialog.AddEmployeeDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.usecasefactory.UseCaseFactory;

public class SwingViewLoader implements ViewLoader {
	
	private SwingViewFactory swingViewFactory;

	public SwingViewLoader(UseCaseFactory useCaseFactory, EventBus eventBus) {
		this.swingViewFactory = new SwingViewFactory(useCaseFactory, eventBus, this);
	}
	
	@Override
	public void loadMainFrameView() {
		swingViewFactory.mainFrameWindow().setVisible(true);
	}

	@Override
	public void loadAddEmployeeDialogView() {
		swingViewFactory.addEmployeeDialog().setVisible(true);
	}

	public void loadUncaugthExceptionView(Throwable e) {
		swingViewFactory.uncaugthExceptionDialog(e).setVisible(true);		
	}
	
}
