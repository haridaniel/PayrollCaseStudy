package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy;

/**
 * @param <T> - binded JPA class
 */
public interface Proxy<T extends Object> {
	T getJPAObject();
}
