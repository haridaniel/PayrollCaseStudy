package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ViewLoader;

public class MainFrameController {

	private MainFrameView mainFrameView;
	private ViewLoader viewLoader;

	public MainFrameController(MainFrameView mainFrameView, ViewLoader viewLoader) {
		this.mainFrameView = mainFrameView;
		this.viewLoader = viewLoader;
	}

	
}
