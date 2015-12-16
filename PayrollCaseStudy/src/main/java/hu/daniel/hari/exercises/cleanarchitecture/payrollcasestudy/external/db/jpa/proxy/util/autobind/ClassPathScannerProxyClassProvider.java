package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.util.autobind;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.inject.Singleton;

import org.reflections.Reflections;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.EmployeeProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.Proxy;

@Singleton
public class ClassPathScannerProxyClassProvider {
	private static final String PACKAGE_TO_SCAN = EmployeeProxy.class.getPackage().getName();

	private Map<Class<?>, Class<?>> proxyClassesByBindedClass = new HashMap<>();

	public static void main(String[] args) {
		new ClassPathScannerProxyClassProvider();
	}

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
		return new Reflections(PACKAGE_TO_SCAN).getTypesAnnotatedWith(AutoBindedProxy.class);
	}

	private Class<Proxy<?>> ensureImplementsProxyInterface(Class<?> class1) {
		if (!Proxy.class.isAssignableFrom(class1))
			throw new DoesNotImplementProxyInterfaceException();
		return (Class<Proxy<?>>) class1;
	}

	public Class<?> getProxyClassFor(Class<?> targetClass) {
		return assertExists(proxyClassesByBindedClass.get(targetClass));
	}

	private Class<?> assertExists(Class<?> proxyClass) {
		if (proxyClass == null)
			throw new NoProxyClassFoundException();
		return proxyClass;
	}

	public static class NoProxyClassFoundException extends RuntimeException {}
	public static class DoesNotImplementProxyInterfaceException extends RuntimeException {}

}
