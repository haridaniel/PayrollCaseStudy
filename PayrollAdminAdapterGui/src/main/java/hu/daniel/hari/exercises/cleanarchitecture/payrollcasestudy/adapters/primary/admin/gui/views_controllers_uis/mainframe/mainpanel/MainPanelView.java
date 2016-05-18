package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.ControlView;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.ModelConsumer;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.ModelProducer;

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
