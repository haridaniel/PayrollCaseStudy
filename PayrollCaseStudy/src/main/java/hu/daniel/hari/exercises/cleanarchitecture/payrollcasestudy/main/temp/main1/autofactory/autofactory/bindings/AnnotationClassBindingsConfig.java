package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.autofactory.bindings;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Configuration;
import org.reflections.Reflections;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.Proxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.util.autobind.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.util.autobind.ClassPathScannerProxyClassProvider.DoesNotImplementProxyInterfaceException;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.PaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.PayClassFormatter;
/**
 * slow
 */
public class AnnotationClassBindingsConfig<T, S> implements ClassBindingsConfig<T, S> {
	private static final Class<Bind> ANNOTATION_CLASS = Bind.class;

	private String scanRootPackage;
	private Class<T> targetSuperClass;
	private Map<Class<? extends S>, Class<? extends T>> targetClassesBySourceClasses = new HashMap<>();
	
	private AnnotationClassBindingsConfig(Class<T> targetSuperClass, String scanRootPackage) {
		this.targetSuperClass = targetSuperClass;
		this.scanRootPackage = scanRootPackage;
		scan();
	}
	
	private void scan() {
		try {
			scanIt();
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void scanIt() throws NoSuchMethodException, SecurityException {
		for (Class<? extends T> targetClass : findTargetClasses()) {
			Bind annotation = targetClass.getAnnotation(ANNOTATION_CLASS);
			if(annotation != null) {
				register((Class<? extends S>) annotation.value(), targetClass);
			}
		}
	}

	private Set<Class<? extends T>> findTargetClasses() {
		return new Reflections(scanRootPackage).getSubTypesOf(targetSuperClass);
	}

	private void register(Class<? extends S> sourceClass, Class<? extends T> targetClass) {
		targetClassesBySourceClasses.put(sourceClass, targetClass);
	}

	@Override
	public Map<Class<? extends S>, Class<? extends T>> getClassBindings() {
		return targetClassesBySourceClasses;
	}

	public static <T, S> ClassBindingsConfig<T, S> of(Class<T> targetSuperClass, Class<S> sourceSuperClass, String scanRootPackage) {
		return new AnnotationClassBindingsConfig<>(targetSuperClass, scanRootPackage);
	}
	
	/**
	 * scans targetSuperClass's package. 
	 */
	public static <T, S> ClassBindingsConfig<T, S> of(Class<T> targetSuperClass, Class<S> sourceSuperClass) {
		return of(targetSuperClass, sourceSuperClass, targetSuperClass.getPackage().getName());
	}
	
}
