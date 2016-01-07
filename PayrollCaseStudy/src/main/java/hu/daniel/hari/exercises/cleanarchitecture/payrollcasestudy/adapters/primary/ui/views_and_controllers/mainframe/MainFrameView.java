package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.views_and_controllers.mainframe;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.views_and_controllers.HasListener;

public interface MainFrameView extends HasListener<MainFrameView.MainFrameViewListener> {

	public interface MainFrameViewListener {
		void onAddEmployeeAction();
	}
}