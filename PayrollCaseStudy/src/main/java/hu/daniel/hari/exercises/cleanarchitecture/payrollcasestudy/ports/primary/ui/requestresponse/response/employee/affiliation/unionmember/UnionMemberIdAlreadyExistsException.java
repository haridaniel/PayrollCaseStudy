package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.affiliation.unionmember;

public class UnionMemberIdAlreadyExistsException extends RuntimeException {
	public int ownerEmployeeId;
	public String ownerEmployeeName;
	public UnionMemberIdAlreadyExistsException(int ownerEmployeeId, String ownerEmployeeName) {
		this.ownerEmployeeId = ownerEmployeeId;
		this.ownerEmployeeName = ownerEmployeeName;
	}
}
