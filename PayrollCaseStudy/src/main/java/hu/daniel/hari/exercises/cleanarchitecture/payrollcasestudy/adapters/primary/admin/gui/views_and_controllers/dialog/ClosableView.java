package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.dialog;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.ControlView;

public interface ClosableView<T extends CloseableViewListener> extends 
	ControlView<T>,
	Closeable
{
	
}