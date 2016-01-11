package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.autofactory.DefaultClassSelectorFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.autofactory.bindings.AnnotationClassBindingsConfig;

public class AnnotationBindedClassSelectorFactory<T, S> extends DefaultClassSelectorFactory<T, S>{

	public AnnotationBindedClassSelectorFactory(Class<T> targetSuperClass, Class<S> sourceSuperClass) {
		super(AnnotationClassBindingsConfig.of(targetSuperClass, sourceSuperClass));
	}

}
