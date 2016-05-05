package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.error.validation;

public interface VisitorAcceptor<T, V extends Visitor<?, T>> {
//	public abstract <T> T accept(Visitor<T> visitor);
//	public abstract <T, V extends Visitor<T>> T accept(V visitor);
//	public abstract <R> R accept(Visitor<R, T> visitor);
	public abstract <R> R accept(V visitor);
}
