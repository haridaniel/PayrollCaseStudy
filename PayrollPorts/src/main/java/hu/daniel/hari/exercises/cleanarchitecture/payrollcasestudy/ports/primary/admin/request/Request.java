package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request;

public interface Request {
	
	public static final EmptyRequest EMPTY_REQUEST = new EmptyRequest();
	public static final class EmptyRequest implements Request {
	}
}