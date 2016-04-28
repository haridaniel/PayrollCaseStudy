package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers;

import javax.inject.Inject;

/** It's role to create the view and the controller, connect them, and 
 *  also provide a single interface to reach them as a UI element / 
 */
public abstract class UI<V extends View, C extends Controller<? super V>> {

	protected final C controller;
	private V view;

	public UI(C controller, V view) {
		this.controller = controller;
		this.view = view;
	}

	@Inject
	private void init() {
		controller.setView(view);
	}
	
	public V getView() {
		return view;
	}
}

