package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.main.temp.main1.autofactory.autofactory.bindings;

import java.util.Map;

/**
 * @param <T> - target
 * @param <S> - source
 */
public interface ClassBindingsConfig<T, S> {

	Map<Class<? extends S>, Class<? extends T>> getClassBindings();

}
