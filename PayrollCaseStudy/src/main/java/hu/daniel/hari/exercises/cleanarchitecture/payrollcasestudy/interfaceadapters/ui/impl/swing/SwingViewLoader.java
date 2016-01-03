package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.impl.swing;

import com.google.common.eventbus.EventBus;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.impl.swing.viewimpl.AddEmployeeDialog;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.impl.swing.viewimpl.MainFrameWindow;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.views_and_controllers.ViewLoader;
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

	@Override
	public void loadUncaugthExceptionView(Throwable e) {
		swingViewFactory.uncaugthExceptionDialog(e).setVisible(true);		
	}
	
}
