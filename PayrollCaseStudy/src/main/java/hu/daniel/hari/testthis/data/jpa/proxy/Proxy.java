package hu.daniel.hari.testthis.data.jpa.proxy;

/**
 * @param <T> - binded JPA class
 */
public interface Proxy<T extends Object> {
	T getJPAObject();
}
