package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy;

public interface ProxyFactory {
	<P extends Proxy<JE>, JE> P create(Class<P> proxyClass, JE jpaEntity);
}