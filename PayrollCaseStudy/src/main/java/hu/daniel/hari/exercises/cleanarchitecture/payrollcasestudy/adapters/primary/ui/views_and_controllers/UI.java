package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers;

import javax.inject.Inject;

public abstract class UI<V extends View, C extends Controller<? super V>> {

	protected C controller;
	public V view;

	public UI(C controller) {
		this.controller = controller;
	}

	protected void setView(V view) {
		this.view = view;
		controller.setView(view);
	}

	
}

