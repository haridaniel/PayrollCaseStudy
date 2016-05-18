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
		SALARIED {@Override public void accept(PaymentType.PaymentTypeVisitor visitor) {visitor.visitSalaried();}},		
		HOURLY {@Override public void accept(PaymentType.PaymentTypeVisitor visitor) {visitor.visitHourly();}},
		COMMISSIONED {@Override public void accept(PaymentType.PaymentTypeVisitor visitor) {visitor.visitCommissioned();}};

		public abstract void accept(PaymentType.PaymentTypeVisitor visitor);

		public interface PaymentTypeVisitor {
			void visitCommissioned();
			void visitHourly();
			void visitSalaried();
		}
	}
	
	public enum AffiliationType {
		NONE,
		UNION_MEMBER
	}

}