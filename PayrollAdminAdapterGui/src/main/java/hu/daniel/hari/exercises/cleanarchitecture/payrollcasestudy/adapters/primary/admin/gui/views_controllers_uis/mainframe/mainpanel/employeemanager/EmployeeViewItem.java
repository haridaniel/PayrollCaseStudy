package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.admin.gui.views_controllers_uis.mainframe.mainpanel.employeemanager;

public class EmployeeViewItem {
	public int id;
	public String name;
	public String address;
	public String waging;
	public String nextPayDay;
	public EmployeeViewItem.PaymentType paymentType;
	public AffiliationType affiliationType;

	public enum PaymentType {
		SALARIED,		
		HOURLY,
		COMMISSIONED
		;
	}
	
	public enum AffiliationType {
		NO,
		UNION_MEMBER
	}

}