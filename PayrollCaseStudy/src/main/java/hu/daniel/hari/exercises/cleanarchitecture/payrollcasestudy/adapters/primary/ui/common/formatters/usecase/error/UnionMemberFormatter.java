package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.common.formatters.usecase.error;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.affiliation.unionmember.UnionMemberIdAlreadyExistsException;

public class UnionMemberFormatter {

	public String format(UnionMemberIdAlreadyExistsException e) {
		return String.format("%s already owns this union member id!", e.ownerEmployeeName);
	}
	
}
