package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.boundary.userapi;


public interface PayrollAppBoundary  {
	
	void addSalariedEmployeeTransaction(int employeeId, String name, String address, int monthlySalary);
	void deleteEmployeeTransaction(int employeeId);
	void changeEmployeeNameTransaction(int employeeId, String newName);
	
}
