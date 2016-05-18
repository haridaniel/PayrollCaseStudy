package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager.affiliationbutton;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.ControlView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.ModelConsumer;

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
