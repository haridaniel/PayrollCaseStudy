package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing;

public class MainFramePresenter {

	private MainFrame mainFrame;
	private ViewFactory viewFactory;

	public MainFramePresenter(MainFrame mainFrame, ViewFactory viewFactory) {
		this.mainFrame = mainFrame;
		this.viewFactory = viewFactory;
	}

	
	
}
