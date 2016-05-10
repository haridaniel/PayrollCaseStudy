package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.impl.gui.swing.viewimpl.mainframe.mainpanel.employeemanager.affiliation;

import com.google.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.employeemanager.affiliationbutton.AffiliationButtonController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.employeemanager.affiliationbutton.AffiliationButtonUI;

public class AffiliationButtonUIImpl extends
	AffiliationButtonUI<AffiliationButtonViewImpl>
{
	@Inject
	public AffiliationButtonUIImpl(
			AffiliationButtonController controller, 
			AffiliationButtonViewImpl view
			) {
		super(controller, view);
	}
	
}
