package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.commissioned;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.CommissionedJPAPaymentClassification;

@Entity
public class JPASalesReceipt {

	@EmbeddedId
	public ID id = new ID();
	
	@Embeddable
	public static class ID implements Serializable {
		public Integer employeeId;
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
	public CommissionedJPAPaymentClassification commissionedJPAPaymentClassification;

	public JPASalesReceipt(LocalDate date, int amount) {
		id.date = date;
		this.amount = amount;
	}

	public void connect(CommissionedJPAPaymentClassification commissionedJPAPaymentClassification) {
		this.commissionedJPAPaymentClassification = commissionedJPAPaymentClassification;
		this.id.employeeId = commissionedJPAPaymentClassification.employeeId;
	}
	
}
