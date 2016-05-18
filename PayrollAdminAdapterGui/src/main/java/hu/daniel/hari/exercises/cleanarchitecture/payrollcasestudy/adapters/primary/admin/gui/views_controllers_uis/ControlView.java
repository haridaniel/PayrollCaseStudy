package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis;

public interface ControlView<VL> extends 
	View
{
	void setViewListener(VL listener);
}
