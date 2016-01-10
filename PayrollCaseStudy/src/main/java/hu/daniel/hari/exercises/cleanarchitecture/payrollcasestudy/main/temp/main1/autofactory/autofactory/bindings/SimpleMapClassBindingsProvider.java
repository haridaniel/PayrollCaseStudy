package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.autofactory.bindings;

import java.util.Map;

public class SimpleMapClassBindingsProvider<T, S> implements ClassBindingsProvider<T, S> {

	Map<Class<? extends S>, Class<? extends T>> targetClassesBySourceClasses;
	
	public SimpleMapClassBindingsProvider(Map<Class<? extends S>, Class<? extends T>> targetClassesBySourceClasses) {
		this.targetClassesBySourceClasses = targetClassesBySourceClasses;
	}
	
	@Override
	public Map<Class<? extends S>, Class<? extends T>> getClassBindings() {
		return targetClassesBySourceClasses;
	}

}
