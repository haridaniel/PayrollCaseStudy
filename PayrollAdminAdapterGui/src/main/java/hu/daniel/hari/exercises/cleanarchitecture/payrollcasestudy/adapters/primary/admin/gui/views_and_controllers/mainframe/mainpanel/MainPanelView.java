package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.ControlView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.ModelConsumer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.ModelProducer;

public interface MainPanelView extends
	ControlView<MainPanelView.MainPanelViewListener>,
	ModelConsumer<MainPanelView.MainPanelViewModel>,
	ModelProducer<MainPanelView.MainPanelViewModel>
{
	
	
	public static interface MainPanelViewListener {
		void onChangedCurrentDate();
	}
	
	public static class MainPanelViewModel {
		public LocalDate currentDate;
		public MainPanelViewModel(LocalDate currentDate) {
			this.currentDate = currentDate;
		}
	}
	
}
