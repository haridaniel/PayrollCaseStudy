package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.viewscontrollers;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.ViewFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing.viewscontrollers.MainFrameView.MainFrameViewListener;

public class MainFrameController implements MainFrameViewListener {

	private MainFrameView mainFrameView;
	private ViewFactory viewFactory;

	public MainFrameController(MainFrameView mainFrameView, ViewFactory viewFactory) {
		this.mainFrameView = mainFrameView;
		this.viewFactory = viewFactory;
		mainFrameView.setListener(this);
	}

	@Override
	public void onAddEmployeeAction() {
		viewFactory.addEmployeeDialogView().setVisible(true);
	}

	
	
}
