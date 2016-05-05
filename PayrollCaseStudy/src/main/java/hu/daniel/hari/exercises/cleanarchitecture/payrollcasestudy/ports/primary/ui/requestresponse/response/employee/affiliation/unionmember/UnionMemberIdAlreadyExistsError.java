package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee.affiliation.unionmember;

public class UnionMemberIdAlreadyExistsError extends AddUnionMemberAffiliationError {
	public int ownerEmployeeId;
	public String ownerEmployeeName;
	public UnionMemberIdAlreadyExistsError(int ownerEmployeeId, String ownerEmployeeName) {
		this.ownerEmployeeId = ownerEmployeeId;
		this.ownerEmployeeName = ownerEmployeeName;
	}
}