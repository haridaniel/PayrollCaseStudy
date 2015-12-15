package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.google.inject.TypeLiteral;

public class ClassPathScannerProxyClassProvider {

	private Map<Class<?>, Class<?>> proxyClassesByTargetClass = new HashMap<>();

	
	public static void main(String[] args) {
		new ClassPathScannerProxyClassProvider();
	}
	
	public ClassPathScannerProxyClassProvider() {
		try {
			scanToMaps();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println(proxyClassesByTargetClass);
	}

	private void scanToMaps() throws NoSuchMethodException, SecurityException {
		Reflections reflections = new Reflections(EmployeeProxy.class.getPackage().getName());
		Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(JPAProxy.class);
		
		for (Class<?> class1 : typesAnnotatedWith) {
			Class<Proxy<?>> proxyClass = ensureProxyClass(class1);
			
			Class<?> jpaClass = getJPAClass(proxyClass);
			
			proxyClassesByTargetClass.put(jpaClass, proxyClass);
			
			System.out.println(jpaClass);
		}
	}

	private Class<?> getJPAClass(Class<Proxy<?>> proxyClass) throws NoSuchMethodException {
		TypeLiteral<? extends Proxy<?>> typeLiteral = TypeLiteral.get(proxyClass);
		Method method = proxyClass.getMethod("getJPAObject");
		TypeLiteral<?> parameterType = typeLiteral.getReturnType(method);
		return (Class<?>) parameterType.getType();
	}

	private Class<Proxy<?>> ensureProxyClass(Class<?> class1) {
		boolean assignableFrom = Proxy.class.isAssignableFrom(class1);
		if(!assignableFrom)
			throw new RuntimeException("not a Proxy");
		return (Class<Proxy<?>>) class1;
	}
	

}
