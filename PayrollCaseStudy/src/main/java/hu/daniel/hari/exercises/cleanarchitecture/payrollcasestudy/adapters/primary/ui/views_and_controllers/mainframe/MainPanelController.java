package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe;

import java.time.LocalDate;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainPanelView.MainPanelViewListener;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.MainPanelView.MainPanelViewModel;

public class MainPanelController implements MainPanelViewListener {
	private MainPanelView view;

	private ObservableValueImpl<LocalDate> observableCurrentDate = new ObservableValueImpl<>();

	@Inject
	public MainPanelController() {
	}

	public void setView(MainPanelView view) {
		this.view = view;
	}

	public void setDefaultModelToView() {
		view.setModel(new MainPanelViewModel(getDefaultDate()));
	}

	private LocalDate getDefaultDate() {
//		return LocalDate.now();
		return LocalDate.of(2016, 4, 15);
	}
	
	public ObservableValue<LocalDate> getObservableCurrentDate() {
		return observableCurrentDate;
	}

	@Override
	public void onChangedCurrentDate() {
		observableCurrentDate.set(view.getModel().currentDate);
	}

}

