package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.util.autobind;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.inject.Singleton;

import org.reflections.Reflections;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.EmployeeProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.Proxy;

@Singleton
public class ClassPathScannerProxyClassProvider {
	private static final String PACKAGE_ROOT_TO_SCAN = EmployeeProxy.class.getPackage().getName();

	private Map<Class<?>, Class<?>> proxyClassesByBindedClass = new HashMap<>();

	public ClassPathScannerProxyClassProvider() {
		init();
	}

	private void init() {
		try {
			scanClassPathToMap();
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

	private void scanClassPathToMap() throws NoSuchMethodException, SecurityException {
		for (Class<?> annotatedClass : getAnnotatedClasses()) {
			Class<Proxy<?>> proxyClass = ensureImplementsProxyInterface(annotatedClass);
			Class<?> bindedClass = proxyClass.getAnnotation(AutoBindedProxy.class).value();
			proxyClassesByBindedClass.put(bindedClass, proxyClass);
		}
	}

	private Set<Class<?>> getAnnotatedClasses() {
		return new Reflections(PACKAGE_ROOT_TO_SCAN).getTypesAnnotatedWith(AutoBindedProxy.class);
	}

	@SuppressWarnings("unchecked")
	private Class<Proxy<?>> ensureImplementsProxyInterface(Class<?> class1) {
		if (!Proxy.class.isAssignableFrom(class1))
			throw new DoesNotImplementProxyInterfaceException();
		return (Class<Proxy<?>>) class1;
	}

	public Class<?> getProxyClassFor(Class<?> targetClass) {
		return assertNotNull(proxyClassesByBindedClass.get(targetClass));
	}

	private Class<?> assertNotNull(Class<?> proxyClass) {
		if (proxyClass == null)
			throw new NoAnnotatedProxyClassFoundException();
		return proxyClass;
	}

	public static class NoAnnotatedProxyClassFoundException extends RuntimeException {}
	public static class DoesNotImplementProxyInterfaceException extends RuntimeException {}

}
