package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis;

public interface Observable<T> {
	T get();
	void addChangeListener(ChangeListener<T> changeListener);
	
	public interface ChangeListener<T> {
		void onChanged(T newValue);
	}
	
}
