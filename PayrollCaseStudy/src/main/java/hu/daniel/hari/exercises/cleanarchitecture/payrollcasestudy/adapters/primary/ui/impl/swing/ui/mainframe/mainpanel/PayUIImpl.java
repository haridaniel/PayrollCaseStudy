package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel.pay.PayListUIImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.mainpanel.PayPanel;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.pay.PayController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.pay.PayUI;

public class PayUIImpl extends
	PayUI<PayPanel>

{
	private PayListUIImpl payListUIImpl;

	@Inject
	public PayUIImpl(
			PayController controller,
			PayListUIImpl payListUIImpl
			) {
		super(controller, payListUIImpl);
		this.payListUIImpl = payListUIImpl;
	}

	@Override
	protected PayPanel createView() {
		return new PayPanel(payListUIImpl.getView());
	}

}
