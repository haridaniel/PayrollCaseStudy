package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.usecase.response;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.common.ThrowMap;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.employeemanager.EmployeeViewItem.AffiliationType;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.employee.AffiliationTypeResponse;

public class AffiliationResponseToEnumConverter extends ThrowMap<AffiliationTypeResponse, AffiliationType> {
	
	public AffiliationResponseToEnumConverter() {
		put(AffiliationTypeResponse.NO, AffiliationType.NONE);
		put(AffiliationTypeResponse.UNION_MEMBER, AffiliationType.UNION_MEMBER);
	}
	
	public AffiliationType toAffiliationType(AffiliationTypeResponse response) {
		return getOrThrow(response);
	}

}
