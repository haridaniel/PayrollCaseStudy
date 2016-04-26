package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui;

import javax.inject.Inject;
import javax.swing.SwingUtilities;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.MainPanelUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.StatusBarUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.MainFrameWindow;

public class MainFrameUI {

	public final MainFrameWindow view;
	
	@Inject
	public MainFrameUI(
			MainPanelUIImpl mainPanelUI,
			StatusBarUIImpl statusBarUI 
			) {
		view = new MainFrameWindow(mainPanelUI.view, statusBarUI.view);
	}
	
	public void show() {
		SwingUtilities.invokeLater(() -> {
			view.setVisible(true);
		});
	}
	
}
