package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.response.error.validation;

public interface Visitable<V extends Visitor<V, A>, A extends Visitable<V, A>> {
	public abstract <R> R accept(V visitor);
}
