package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.commissioned;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.CommissionedJPAPaymentType;

@Entity
public class JPASalesReceipt {

	@EmbeddedId
	public ID id = new ID();
	
	@Embeddable
	public static class ID implements Serializable {
		public Integer employeeId;

		@Column(name="\"date\"")
		public LocalDate date;

		public ID() {
		}
		public ID(Integer employeeId, LocalDate date) {
			this.employeeId = employeeId;
			this.date = date;
		}
	}
	
	public int amount;

	@ManyToOne  	
	@JoinColumn(name="employeeId", referencedColumnName="employeeId", insertable = false, updatable = false)
	public CommissionedJPAPaymentType commissionedJPAPaymentType;

	public JPASalesReceipt() {}
	public JPASalesReceipt(LocalDate date, int amount) {
		id.date = date;
		this.amount = amount;
	}

	public void connect(CommissionedJPAPaymentType commissionedJPAPaymentType) {
		this.commissionedJPAPaymentType = commissionedJPAPaymentType;
		this.id.employeeId = commissionedJPAPaymentType.employeeId;
	}
	
}
