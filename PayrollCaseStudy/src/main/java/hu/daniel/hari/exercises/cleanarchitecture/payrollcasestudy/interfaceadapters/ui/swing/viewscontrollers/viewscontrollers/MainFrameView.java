package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.viewscontrollers;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.ui.swing.viewscontrollers.HasListener;

public interface MainFrameView extends HasListener<MainFrameView.MainFrameViewListener> {

	public interface MainFrameViewListener {
		void onAddEmployeeAction();
	}
}