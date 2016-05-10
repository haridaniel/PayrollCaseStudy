package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.response;

public interface Response {
	
	public static final EmptyResponse EMPTY_RESPONSE = new EmptyResponse();
	public static final class EmptyResponse implements Response {
	}
	
	
}