package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.employeemanager.affiliationbutton;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.ControlView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.ModelConsumer;

public interface AffiliationButtonView extends
	ControlView<AffiliationButtonView.AffiliationButtonViewListener>,
	ModelConsumer<AffiliationButtonView.AffiliationButtonViewModel>
{
	public interface AffiliationButtonViewListener {
		void onActionPerformed();
	}
	public static class AffiliationButtonViewModel {
		public boolean enabled;
		public String buttonText;
	}
	
}
