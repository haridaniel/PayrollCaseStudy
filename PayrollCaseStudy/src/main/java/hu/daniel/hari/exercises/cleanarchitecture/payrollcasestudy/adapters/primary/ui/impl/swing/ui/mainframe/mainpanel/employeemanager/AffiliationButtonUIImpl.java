package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.ui.mainframe.mainpanel.employeemanager;

import com.google.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.mainframe.mainpanel.employeemanager.affiliation.AffiliationButtonViewImpl;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.affiliationbutton.AffiliationButtonController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.affiliationbutton.AffiliationButtonUI;

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
