package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.affiliation;

import java.util.HashMap;
import java.util.Map;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.EmployeeViewItem.AffiliationType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AffiliationTypeResponse;

public class AffiliationResponseToEnumConverter {
	
	private Map<AffiliationTypeResponse, AffiliationType> map = new HashMap<AffiliationTypeResponse, AffiliationType>() {{
		put(AffiliationTypeResponse.NO, AffiliationType.NONE);
		put(AffiliationTypeResponse.UNION_MEMBER, AffiliationType.UNION_MEMBER);
	}};

	public AffiliationType toAffiliationType(AffiliationTypeResponse affiliationTypeResponse) {
		return map.get(affiliationTypeResponse);
	}

}
