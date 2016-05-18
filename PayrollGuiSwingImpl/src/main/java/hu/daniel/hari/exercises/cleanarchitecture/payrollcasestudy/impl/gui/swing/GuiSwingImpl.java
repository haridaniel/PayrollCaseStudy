package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing;

import com.google.inject.Guice;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.MainFrameUI;

public class GuiSwingImpl implements Runnable {
	private MainFrameUI mainFrameUI;

	public GuiSwingImpl(UseCaseFactoriesForGUI useCaseFactories) {
		mainFrameUI = Guice.createInjector(new GuiSwingModule(useCaseFactories)).getInstance(MainFrameUI.class);
	}

	@Override
	public void run() {
		mainFrameUI.show();
	}
	
}
