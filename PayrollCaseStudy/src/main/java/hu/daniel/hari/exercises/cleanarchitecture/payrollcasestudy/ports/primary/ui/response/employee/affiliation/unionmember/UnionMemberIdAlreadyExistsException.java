package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.response.employee.affiliation.unionmember;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.response.exception.UseCaseException;

public class UnionMemberIdAlreadyExistsException extends UseCaseException {
	public int ownerEmployeeId;
	public String ownerEmployeeName;
	public UnionMemberIdAlreadyExistsException(int ownerEmployeeId, String ownerEmployeeName) {
		this.ownerEmployeeId = ownerEmployeeId;
		this.ownerEmployeeName = ownerEmployeeName;
	}
}
