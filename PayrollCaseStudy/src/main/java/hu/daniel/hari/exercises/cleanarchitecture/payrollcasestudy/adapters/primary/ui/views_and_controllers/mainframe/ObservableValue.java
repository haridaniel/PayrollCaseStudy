package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe;

public interface ObservableValue<T> {
	T get();
	void addChangeListener(ChangeListener<T> changeListener);
	
	public interface ChangeListener<T> {
		void onChanged(T newValue);
	}
	
}
