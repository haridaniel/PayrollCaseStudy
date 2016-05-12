package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.util.autobind;

import java.lang.reflect.Constructor;

import javax.inject.Inject;

import com.google.inject.Injector;
import com.google.inject.Singleton;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.util.autobind.ClassPathScannerProxyClassProvider;

@Singleton
public class AutoBindedProxyFactory {

	@Inject private ClassPathScannerProxyClassProvider classPathScannerProxyClassProvider;
	@Inject private Injector injector;
	
	public <T> T create(Class<T> proxyClass, Object jpaEntity) {
		return (T) createInjectedProxyFor(jpaEntity);
	}

	private Object createInjectedProxyFor(Object jpaEntity) {
		Object proxy = createProxyFor(jpaEntity);
		injector.injectMembers(proxy);
		return proxy;
	}

	private Object createProxyFor(Object jpaEntity) {
		Class<?> proxyClass = classPathScannerProxyClassProvider.getProxyClassFor(jpaEntity.getClass());
		return newInstanceWithConstructorParameter(proxyClass, jpaEntity);
	}
		
	private <T> T newInstanceWithConstructorParameter(Class<T> type, Object parameter) {
		try {
			Constructor<T> constructor = type.getDeclaredConstructor(parameter.getClass());
			return (T) constructor.newInstance(parameter);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
