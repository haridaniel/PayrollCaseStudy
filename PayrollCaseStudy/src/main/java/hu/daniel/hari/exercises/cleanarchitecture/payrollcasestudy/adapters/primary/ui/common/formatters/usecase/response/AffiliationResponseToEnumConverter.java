package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.usecase.response;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.common.ThrowMap;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.mainpanel.employeemanager.EmployeeViewItem.AffiliationType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.AffiliationTypeResponse;

public class AffiliationResponseToEnumConverter extends ThrowMap<AffiliationTypeResponse, AffiliationType> {
	
	public AffiliationResponseToEnumConverter() {
		put(AffiliationTypeResponse.NO, AffiliationType.NONE);
		put(AffiliationTypeResponse.UNION_MEMBER, AffiliationType.UNION_MEMBER);
	}
	
	public AffiliationType toAffiliationType(AffiliationTypeResponse response) {
		return getOrThrow(response);
	}

}
