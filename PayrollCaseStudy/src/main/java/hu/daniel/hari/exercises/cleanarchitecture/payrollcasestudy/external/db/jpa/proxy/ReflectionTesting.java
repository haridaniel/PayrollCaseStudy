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
//		test1();
		test2();
	}


	private void test2() {
		Reflections reflections = new Reflections(EmployeeProxy.class.getPackage().getName());
		Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(JPAProxy.class);
		
		for (Class<?> class1 : typesAnnotatedWith) {
			JPAProxy annotation = class1.getAnnotation(JPAProxy.class);
			
			
			System.out.println();
		}
		
	}


	
}
