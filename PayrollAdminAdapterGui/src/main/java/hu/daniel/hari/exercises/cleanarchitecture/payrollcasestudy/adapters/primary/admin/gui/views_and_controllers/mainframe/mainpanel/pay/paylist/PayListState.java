package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_and_controllers.mainframe.mainpanel.pay.paylist;

public class PayListState {
	public int itemCount;
	public boolean isEmpty;
	
	public PayListState(int itemCount, boolean isEmpty) {
		this.itemCount = itemCount;
		this.isEmpty = isEmpty;
	}
	
}
