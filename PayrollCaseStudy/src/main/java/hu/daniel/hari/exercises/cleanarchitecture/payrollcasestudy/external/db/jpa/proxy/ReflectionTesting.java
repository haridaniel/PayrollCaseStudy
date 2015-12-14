package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;


public class ReflectionTesting {

	public static void main(String[] args) {
		new ReflectionTesting();
	}
	
	public ReflectionTesting() {
		
		
		Reflections reflections = new Reflections(EmployeeProxy.class.getPackage().getName());
		
		Set<Class<? extends Proxy>> classes = reflections.getSubTypesOf(Proxy.class);
		
		System.out.println(classes.size());
		
		for (Class<? extends Proxy> proxyClass : classes) {
			System.out.println("get type for " + proxyClass);
			System.out.println((Class<Proxy>) getProxyGenericType(proxyClass));
			// Nem fog menni!
		}
		
	}

	private List<Type> getGenericInterfaces1(Class<? extends Proxy> class1) {
		List<Type> genericInterfaces = Arrays.asList(class1.getGenericInterfaces());
		for (Type genericInterface : genericInterfaces) {
		    if (genericInterface instanceof ParameterizedType) {
		    	
		        Type[] genericTypes = ((ParameterizedType) genericInterface).getActualTypeArguments();
		        for (Type genericType : genericTypes) {
		            System.out.println("Generic type: " + genericType);
		        }
		    }
		}
		return genericInterfaces;
	}
	private Type getProxyGenericType(Class<? extends Proxy> class1) {
		List<Type> genericInterfaces = Arrays.asList(class1.getGenericInterfaces());
		ParameterizedType parameterisedType = genericInterfaces.stream()
				.filter(t -> t instanceof ParameterizedType)
				.map(t -> (ParameterizedType) t)
				.filter(t -> ( ((Class)((ParameterizedType)t).getRawType()).isAssignableFrom(Proxy.class)))
				.findFirst()
				.get();
		Type genericType = Arrays.asList(parameterisedType.getActualTypeArguments()).stream()
				.findFirst()
				.get();
		return genericType;
	}
	
}
