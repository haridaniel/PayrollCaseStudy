package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy;

import java.lang.reflect.Constructor;

import javax.inject.Inject;

import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class ProxyFactory {

	private ProxyAssignmentConfiguration proxyAssignmentConfiguration;
	
	private @Inject Injector injector;
	private @Inject ReflectionTesting reflectionTesting;
	
	@Inject
	public ProxyFactory(ProxyAssignmentConfiguration proxyAssignmentConfiguration) {
		this.proxyAssignmentConfiguration = proxyAssignmentConfiguration;
	}
	
	public <T> T create(Class<T> proxyClass, Object jpaEntity) {
		T proxy = (T) createProxyFor(jpaEntity);
		injector.injectMembers(proxy);
		return proxy;
	}

	private Object createProxyFor(Object jpaEntity) {
		return newInstanceWithParameter(proxyAssignmentConfiguration.getProxyClassFor(jpaEntity), jpaEntity);
	}
		
	private <T> T newInstanceWithParameter(Class<T> type, Object parameter) {
		try {
			Constructor<T> constructor = type.getDeclaredConstructor(parameter.getClass());
			return (T) constructor.newInstance(parameter);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
