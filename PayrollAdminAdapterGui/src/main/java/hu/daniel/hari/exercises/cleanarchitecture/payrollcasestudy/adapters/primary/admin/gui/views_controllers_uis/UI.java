package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis;

import javax.inject.Inject;

/** Represents a composition of a {@link View} and a {@link Controller}.
 *
 * 	It's role to create(or ask for) the view and the controller, connect them, and 
 *  provide a single interface to reach them as a "UI element".  
 */
public abstract class UI<V extends View, C extends Controller<? super V>> {

	protected final C controller;
	private V view;

	public UI(C controller, V view) {
		this.controller = controller;
		this.view = view;
	}

	@Inject
	protected void init() {
		controller.setView(view);
	}
	
	public V getView() {
		return view;
	}
}

