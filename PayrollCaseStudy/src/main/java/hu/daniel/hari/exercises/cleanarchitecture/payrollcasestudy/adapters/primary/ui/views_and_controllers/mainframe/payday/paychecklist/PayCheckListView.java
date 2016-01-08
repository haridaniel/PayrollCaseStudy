package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.payday.paychecklist;

import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.ModelConsumer;

public interface PayCheckListView extends
	ModelConsumer<PayCheckListView.PayCheckListViewModel>
{
		
	public static class PayCheckListViewModel {
		public List<PayCheckViewModel> payCheckViewModels;
		
		public PayCheckListViewModel(List<PayCheckViewModel> payCheckViewModels) {
			this.payCheckViewModels = payCheckViewModels;
		}

		public static class PayCheckViewModel {
			public int id;
			public String name;
			public String waging;
			public int grossAmount;
			public int deductionsAmount;
			public int netAmount;
			public String paymentMethod;
		}
		
	}
}
