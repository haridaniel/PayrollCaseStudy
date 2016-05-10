package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.mainframe.mainpanel;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.pay.PayController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.pay.PayUI;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.ui.swing.viewimpl.mainframe.mainpanel.pay.PayListUIImpl;

public class PayUIImpl extends
	PayUI<PayPanel>
{

	@Inject
	public PayUIImpl(
			PayController controller,
			PayListUIImpl payListUIImpl
			) {
		super(controller, new PayPanel(payListUIImpl.getView()), payListUIImpl);
	}

}
