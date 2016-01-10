package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.autofactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.autofactory.bindings.ClassBindingsProvider;

public abstract class DefaultAutoFactory<T, S> implements AutoFactory<T, S> {
	
	private Map<Class<? extends S>, Class<? extends T>> targetClassesBySourceClasses;
	
	public DefaultAutoFactory(ClassBindingsProvider<T, S> classBindingsProvider) {
		targetClassesBySourceClasses = classBindingsProvider.getClassBindings();
	}
	
	@Override
	public <CS extends S> T create(Class<CS> selectorClass) {
		return newInstance(getTargetClass(selectorClass)); 
	}


	private <CS extends S> Class<? extends T> getTargetClass(Class<CS> selectorClass) {
		for (Class<? extends S> paymentClassificationClass : targetClassesBySourceClasses.keySet()) {
			boolean assignableFrom = paymentClassificationClass.isAssignableFrom(selectorClass);
			if(assignableFrom)
				return targetClassesBySourceClasses.get(paymentClassificationClass);
		}
		throw new RuntimeException("not found");
	}

	private <CT> CT newInstance(Class<CT> type) {
		try {
			Constructor<CT> constructor = type.getDeclaredConstructor();
			return (CT) constructor.newInstance();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

}
