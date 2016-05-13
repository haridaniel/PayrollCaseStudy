package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response;

public interface Response {
	
	@Deprecated
	public static final class EmptyResponse implements Response {
		public static final EmptyResponse INSTANCE = new EmptyResponse();
	}
	
	
}