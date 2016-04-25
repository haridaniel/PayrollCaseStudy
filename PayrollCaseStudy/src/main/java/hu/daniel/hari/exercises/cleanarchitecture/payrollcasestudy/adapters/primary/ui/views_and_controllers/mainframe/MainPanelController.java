package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.AbstractController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainPanelView.MainPanelViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainPanelView.MainPanelViewModel;

public class MainPanelController extends 
	AbstractController<MainPanelView, MainPanelViewListener> implements
	MainPanelViewListener 
{
	private ObservableValueImpl<LocalDate> observableCurrentDate = new ObservableValueImpl<>();

	public void setDefaultModelToView() {
		view.setModel(new MainPanelViewModel(getDefaultDate()));
	}

	private LocalDate getDefaultDate() {
//		return LocalDate.now();
		return getDebugDate();
	}

	@Deprecated
	private LocalDate getDebugDate() {
//		return LocalDate.of(2016, 4, 14);
		return LocalDate.of(2016, 4, 15);
	}
	
	public ObservableValue<LocalDate> getObservableCurrentDate() {
		return observableCurrentDate;
	}

	@Override
	public void onChangedCurrentDate() {
		observableCurrentDate.set(view.getModel().currentDate);
	}

	@Override
	protected MainPanelViewListener getViewListener() {
		return this;
	}

}

