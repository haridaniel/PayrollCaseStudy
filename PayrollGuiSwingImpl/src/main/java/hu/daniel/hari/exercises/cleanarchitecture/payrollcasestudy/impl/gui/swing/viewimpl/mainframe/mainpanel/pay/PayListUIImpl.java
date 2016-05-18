package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.mainframe.mainpanel.pay;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.pay.paylist.PayListController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.pay.paylist.PayListUI;

public class PayListUIImpl extends PayListUI<PayListPanel> {

	@Inject
	public PayListUIImpl(
			PayListController controller,
			PayListPanel view
			) {
		super(controller, view);
	}

}
