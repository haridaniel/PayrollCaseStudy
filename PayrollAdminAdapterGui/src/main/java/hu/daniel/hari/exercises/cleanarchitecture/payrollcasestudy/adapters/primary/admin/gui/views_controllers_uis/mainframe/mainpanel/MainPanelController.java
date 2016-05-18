package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.AbstractController;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.Observable;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.ObservableValue;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.MainPanelView.MainPanelViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.MainPanelView.MainPanelViewModel;

public class MainPanelController extends 
	AbstractController<MainPanelView, MainPanelViewListener> implements
	MainPanelViewListener 
{
	private ObservableValue<LocalDate> observableCurrentDate = new ObservableValue<>();

	public void setDefaultModelToView() {
		getView().setModel(new MainPanelViewModel(getDefaultDate()));
	}

	private LocalDate getDefaultDate() {
		return LocalDate.now();
//		return LocalDate.of(2016, 4, 14); //DEBUG
//		return LocalDate.of(2016, 4, 15); //DEBUG
	}

	public Observable<LocalDate> getObservableCurrentDate() {
		return observableCurrentDate;
	}

	@Override
	public void onChangedCurrentDate() {
		observableCurrentDate.set(getView().getModel().currentDate);
	}

	@Override
	protected MainPanelViewListener getViewListener() {
		return this;
	}

}

