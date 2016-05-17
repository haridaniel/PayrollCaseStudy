package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy;

/**
 * Should have a constructor with the JPA object parameter.
 * @param <T> - binded JPA class
 */
public interface Proxy<T extends Object> {
	T getJPAObject();
}
