package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy;

/**
 * @param <T> - JPA Entity type
 */
public interface Proxy<T extends Object> {
	Class<T> getJPAClass();
	T getJPAObject();
}
