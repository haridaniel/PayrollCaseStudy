package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ViewLoader;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainFrameView.MainFrameViewListener;

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
