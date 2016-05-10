package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers;

public interface ControlView<VL> extends 
	View
{
	void setViewListener(VL listener);
}
