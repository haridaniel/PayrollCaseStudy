package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.inject.Singleton;

import org.reflections.Reflections;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.Proxy;

/** Scans for Proxy classes under Proxy interface's package. 
 * The proxies should be annotated with AutoBindedProxy.**/
@Singleton
public class ClassPathScannerProxyClassProvider {
	private static final String PACKAGE_ROOT_TO_SCAN = Proxy.class.getPackage().getName();

	private Map<Class<?>, Class<Proxy<?>>> proxyClassesByBindedClass = new HashMap<>();

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
			Class<Proxy<?>> proxyClass = asProxyClass(annotatedClass);
			Class<?> bindedClass = proxyClass.getAnnotation(AutoBindedProxy.class).value();
			proxyClassesByBindedClass.put(bindedClass, proxyClass);
		}
	}

	private Set<Class<?>> getAnnotatedClasses() {
		return new Reflections(PACKAGE_ROOT_TO_SCAN).getTypesAnnotatedWith(AutoBindedProxy.class);
	}

	@SuppressWarnings("unchecked")
	private Class<Proxy<?>> asProxyClass(Class<?> clazz) {
		if (!Proxy.class.isAssignableFrom(clazz))
			throw new AnnotatedClassDoesNotImplementsProxyInterfaceException(clazz);
		return (Class<Proxy<?>>) clazz;
	}
	
	/** not type safe. can't check proxy class's type parameter**/ 
	public Class<Proxy<?>> getProxyClassFor(Class<?> targetClass) {
		checkExists(targetClass);
		return proxyClassesByBindedClass.get(targetClass);
	}

	private void checkExists(Class<?> targetClass) {
		if(!proxyClassesByBindedClass.containsKey(targetClass))
			throw new NoAnnotatedProxyClassFoundException(targetClass);
	}

	public static class NoAnnotatedProxyClassFoundException extends RuntimeException {
		public NoAnnotatedProxyClassFoundException(Class<?> targetClass) {
			super(String.format("for target class %s", targetClass));
		}
	}
	public static class AnnotatedClassDoesNotImplementsProxyInterfaceException extends RuntimeException {
		public AnnotatedClassDoesNotImplementsProxyInterfaceException(Class<?> clazz) {
			super(String.format(": %s", clazz));
		}}

}
