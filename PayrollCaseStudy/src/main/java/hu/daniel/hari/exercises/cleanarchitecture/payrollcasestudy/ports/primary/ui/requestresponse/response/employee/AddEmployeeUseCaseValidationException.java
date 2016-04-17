package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.employee;

import java.util.List;

public class AddEmployeeUseCaseValidationException extends RuntimeException {
	public final List<AddEmployeeValidationError> addEmployeeValidationErrors;
	public AddEmployeeUseCaseValidationException(List<AddEmployeeValidationError> addEmployeeValidationErrors) {
		this.addEmployeeValidationErrors = addEmployeeValidationErrors;
	}
	
	public interface AddEmployeeValidationError {
		
		public abstract <T> T accept(AddEmployeeValidationErrorVisitor<T> visitor);
		
		public interface AddEmployeeValidationErrorVisitor<T> {
			T visit(IdAlreadyExistsValidationError idAlreadyExistsValidationError);
			T visit(NameAlreadyExistsValidationError nameAlreadyExistsValidationError);
		}
	}
	
	public static class IdAlreadyExistsValidationError implements AddEmployeeValidationError {
		public String nameOfExistingUser;
		public IdAlreadyExistsValidationError(String nameOfExistingUser) {
			this.nameOfExistingUser = nameOfExistingUser;
		}
		@Override
		public <T> T accept(AddEmployeeValidationErrorVisitor<T> visitor) {
			return visitor.visit(this);
		}
	}
	
	public static class NameAlreadyExistsValidationError implements AddEmployeeValidationError {
		public int idOfExistingUser;
		public NameAlreadyExistsValidationError(int idOfExistingUser) {
			this.idOfExistingUser = idOfExistingUser;
		}
		@Override
		public <T> T accept(AddEmployeeValidationErrorVisitor<T> visitor) {
			return visitor.visit(this);
		}
	}

}