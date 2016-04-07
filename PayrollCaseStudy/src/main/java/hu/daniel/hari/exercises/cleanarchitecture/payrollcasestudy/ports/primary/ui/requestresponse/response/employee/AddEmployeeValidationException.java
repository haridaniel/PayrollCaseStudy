package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee;

import java.util.List;

public class AddEmployeeValidationException extends RuntimeException {
	public final List<AddEmployeeValidationError> addEmployeeValidationErrors;
	public AddEmployeeValidationException(List<AddEmployeeValidationError> addEmployeeValidationErrors) {
		this.addEmployeeValidationErrors = addEmployeeValidationErrors;
	}
	
	public interface AddEmployeeValidationError {
	}
	
	public static class IdAlreadyExistsValidationError implements AddEmployeeValidationError {
		public String nameOfExistingUser;
		public IdAlreadyExistsValidationError(String nameOfExistingUser) {
			this.nameOfExistingUser = nameOfExistingUser;
		}
	}

	public static class NameAlreadyExistsValidationError implements AddEmployeeValidationError {
		public int idOfExistingUser;
		public NameAlreadyExistsValidationError(int idOfExistingUser) {
			this.idOfExistingUser = idOfExistingUser;
		}
	}

}