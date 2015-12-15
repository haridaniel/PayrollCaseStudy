package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.google.inject.TypeLiteral;


public class ReflectionTesting {

	public static void main(String[] args) throws Exception {
		new ReflectionTesting();
	}
	
	
	public ReflectionTesting() throws NoSuchMethodException, SecurityException {
		
		Reflections reflections = new Reflections(EmployeeProxy.class.getPackage().getName());
		
		Set<Class<? extends Proxy>> allProxyClasses = reflections.getSubTypesOf(Proxy.class);
		Set<Class<? extends ValueAssignedProxy>> valueAssignedProxyClasses = reflections.getSubTypesOf(ValueAssignedProxy.class);
		
		Set<Class<? extends Proxy>> proxyClasses = new HashSet<>(allProxyClasses);
		proxyClasses.removeAll(valueAssignedProxyClasses);
		
		

		Map<Class<?>, Class<?>> proxyClassesByJPAEntityClass = new HashMap<>();

		
		for (Class<? extends Proxy> proxyClass : allProxyClasses) {
			
			TypeLiteral<? extends Proxy> typeLiteral = TypeLiteral.get(proxyClass);
			Method method = proxyClass.getMethod("getJPAObject");
			
			
			TypeLiteral<?> parameterType = typeLiteral.getReturnType(method);
			Type type = parameterType.getType();
//			Class<?> jpaClass = (Class<?>) type;
			System.out.println(type);
			
//			proxyClassesByJPAEntityClass.put(proxyClass.getClass(), value)
			
			System.out.println();
		}
		
		
		
		System.out.println(proxyClasses.size());
	}

	
}
