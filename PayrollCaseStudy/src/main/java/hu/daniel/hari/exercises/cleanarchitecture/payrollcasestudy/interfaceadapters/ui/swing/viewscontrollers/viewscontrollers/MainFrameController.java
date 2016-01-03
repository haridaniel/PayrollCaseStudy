package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.ViewLoader;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers.MainFrameView.MainFrameViewListener;

public class MainFrameController implements MainFrameViewListener {

	private MainFrameView mainFrameView;
	private ViewLoader viewLoader;

	public MainFrameController(MainFrameView mainFrameView, ViewLoader viewLoader) {
		this.mainFrameView = mainFrameView;
		this.viewLoader = viewLoader;
	}

	@Override
	public void onAddEmployeeAction() {
		viewLoader.loadAddEmployeeDialogView();
	}
	
}
