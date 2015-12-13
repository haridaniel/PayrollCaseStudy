package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy;

import java.util.Set;

import org.reflections.Reflections;


public class ReflectionTesting {

	
	public ReflectionTesting() {
		
		
		Reflections reflections = new Reflections(EmployeeProxy.class.getPackage().getName());
		
		Set<Class<? extends Proxy>> classes = reflections.getSubTypesOf(Proxy.class);
		
		System.out.println(classes.size());
		
		
	}
	
}
