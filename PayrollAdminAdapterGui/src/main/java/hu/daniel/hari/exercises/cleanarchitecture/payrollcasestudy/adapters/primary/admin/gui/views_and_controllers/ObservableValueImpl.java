package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers;

import java.util.HashSet;
import java.util.Set;

public class ObservableValueImpl<T> implements ObservableValue<T>{

	private T value;
	private Set<ChangeListener<T>> changeListeners = new HashSet<>();

	public ObservableValueImpl(T initialValue) {
		this.value = initialValue;
	}
	
	public ObservableValueImpl() {
		this(null);
	}
	
	public void set(T value) {
		this.value = value;
		notifyListeners();
	}
	
	private void notifyListeners() {
		changeListeners.stream().forEach(
			l -> l.onChanged(value)
		);
	}

	@Override
	public T get() {
		return value;
	}

	@Override
	public void addChangeListener(ChangeListener<T> changeListener) {
		changeListeners.add(changeListener);
	}
	
}
