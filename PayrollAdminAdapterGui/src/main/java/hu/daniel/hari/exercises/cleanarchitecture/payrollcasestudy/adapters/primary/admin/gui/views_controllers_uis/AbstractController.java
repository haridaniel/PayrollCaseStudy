package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis;

public abstract class AbstractController<V extends ControlView<VL>, VL> implements 
	Controller<V> 
{
	private V view;

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
