package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.factory;

import java.lang.reflect.Constructor;

import javax.inject.Inject;

import com.google.inject.Injector;
import com.google.inject.Singleton;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.Proxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.ProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.factory.ClassPathScannerProxyClassProvider;

@Singleton
public class AutoBindedProxyFactory implements ProxyFactory {

	@Inject private ClassPathScannerProxyClassProvider classPathScannerProxyClassProvider;
	@Inject private Injector injector;
	
	/** proxy type is unchecked **/ 
	@Override
	@SuppressWarnings("unchecked")
	public <P extends Proxy<JE>, JE> P create(Class<P> proxyClass, JE jpaEntity) {
		return (P) createInjectedProxyFor(jpaEntity);
	}

	private Proxy<?> createInjectedProxyFor(Object jpaEntity) {
		Proxy<?> proxy = createProxyFor(jpaEntity);
		injector.injectMembers(proxy);
		return proxy;
	}

	private Proxy<?> createProxyFor(Object jpaEntity) {
		return newInstanceWithConstructorParameter(
				classPathScannerProxyClassProvider.getProxyClassFor(jpaEntity.getClass()), 
				jpaEntity);
	}
		
	private static <T> T newInstanceWithConstructorParameter(Class<T> type, Object parameter) {
		try {
			Constructor<T> constructor = type.getDeclaredConstructor(parameter.getClass());
			return (T) constructor.newInstance(parameter);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
