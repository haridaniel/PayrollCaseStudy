package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel;

import java.time.LocalDate;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel.pay.PayListPanelUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.mainpanel.PayPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay.PayController;

public class PayPanelUI {

	public final PayPanel view;
	private PayController controller;
	private PayListPanelUI payListPanelUI;

	@Inject
	public PayPanelUI(
			PayController controller,
			PayListPanelUI payListPanelUI
			) {
		this.controller = controller;
		this.payListPanelUI = payListPanelUI;
		view = new PayPanel(payListPanelUI.view);
		view.setListener(controller);
		controller.setView(view);
		payListPanelUI.getObservablePayListState().addChangeListener(controller);
	}

	public void setObservableCurrentDate(ObservableValue<LocalDate> observableCurrentDate) {
		controller.setObservableCurrentDate(observableCurrentDate);
		payListPanelUI.setObservableCurrentDate(observableCurrentDate);
	}
	

}
