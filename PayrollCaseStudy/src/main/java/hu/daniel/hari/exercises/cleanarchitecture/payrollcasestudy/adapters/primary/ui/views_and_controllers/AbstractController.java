package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers;

public abstract class AbstractController<V extends ControlView<VL>, VL> implements 
	Controller<V> 
{
	@Deprecated
	//Todo:remove
	protected V view;

	@Override
	public void setView(V view) {
		this.view = view;
		view.setViewListener(getViewListener());
	}

	protected V getView() {
		return view;
	}
	
	protected abstract VL getViewListener();

}
