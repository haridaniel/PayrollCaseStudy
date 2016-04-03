package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe;

import javax.inject.Inject;

public class MainFrameController {

	private MainFrameView mainFrameView;

	@Inject
	public MainFrameController() {
	}
	
	public void setView(MainFrameView mainFrameView) {
		this.mainFrameView = mainFrameView;
	}

	
}
