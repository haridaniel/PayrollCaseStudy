package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.views_and_controllers.mainframe.pay.paylist;

public class PayListState {
	@Deprecated
	public int itemCount;
	public boolean isEmpty;
	
	public PayListState(int itemCount, boolean isEmpty) {
		this.itemCount = itemCount;
		this.isEmpty = isEmpty;
	}
	
}
