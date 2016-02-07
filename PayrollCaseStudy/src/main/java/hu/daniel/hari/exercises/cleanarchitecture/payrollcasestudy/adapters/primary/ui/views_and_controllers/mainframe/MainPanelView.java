package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.HasListener;

public interface MainPanelView extends HasListener<MainPanelView.MainPanelViewListener> {

	public interface MainPanelViewListener {
		void onAddEmployeeAction();
	}
}