package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing;

import com.google.inject.Guice;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.MainFrameUI;

public class SwingGuiImpl {
	public SwingGuiImpl(UseCaseFactoriesForGUI useCaseFactories) {
		MainFrameUI mainFrameUI = Guice.createInjector(new SwingUIModule(useCaseFactories)).getInstance(MainFrameUI.class);
		mainFrameUI.show();
	}
	
}
