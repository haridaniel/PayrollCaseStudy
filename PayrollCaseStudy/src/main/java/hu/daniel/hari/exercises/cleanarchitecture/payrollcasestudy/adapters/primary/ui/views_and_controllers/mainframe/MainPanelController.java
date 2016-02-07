package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ViewLoader;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainPanelView.MainPanelViewListener;

public class MainPanelController implements MainPanelViewListener {

	private MainPanelView mainPanelView;
	private ViewLoader viewLoader;

	public MainPanelController(MainPanelView mainPanelView, ViewLoader viewLoader) {
		this.mainPanelView = mainPanelView;
		this.viewLoader = viewLoader;
	}

	@Override
	public void onAddEmployeeAction() {
		viewLoader.loadAddEmployeeDialogView();
	}
	
}
