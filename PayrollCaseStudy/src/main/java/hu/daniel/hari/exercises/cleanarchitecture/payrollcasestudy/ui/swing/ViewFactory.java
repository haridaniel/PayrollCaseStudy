package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ui.swing;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.usecasefactory.UseCaseFactory;

public class ViewFactory {

	private UseCaseFactory useCaseFactory;

	public ViewFactory(UseCaseFactory useCaseFactory) {
		this.useCaseFactory = useCaseFactory;
	}
	
	private EmployeesOverviewPanelView employeesOverviewPanelView() {
		EmployeesOverviewPanelView view = new EmployeesOverviewPanelView();
		new EmployeesOverviewPanelPresenter(view, useCaseFactory);
		return view;
	}
	
	public MainFrame mainFrame() {
		MainFrame mainFrame = new MainFrame(employeesOverviewPanelView());
		new MainFramePresenter(mainFrame, this);
		return mainFrame;
	}

	
	
}
