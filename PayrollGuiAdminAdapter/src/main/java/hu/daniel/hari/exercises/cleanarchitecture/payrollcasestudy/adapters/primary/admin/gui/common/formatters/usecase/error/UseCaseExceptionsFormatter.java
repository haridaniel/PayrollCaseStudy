package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.common.formatters.usecase.error;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.employee.affiliation.unionmember.UnionMemberIdAlreadyExistsException;

public class UseCaseExceptionsFormatter {

	public String format(UnionMemberIdAlreadyExistsException e) {
		return String.format("%s already owns this union member id!", e.ownerEmployeeName);
	}
	
}
