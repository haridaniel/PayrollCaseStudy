package hu.daniel.hari.testthis.data.jpa.model.paymentclassification.hourly;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import hu.daniel.hari.testthis.data.jpa.model.paymentclassification.HourlyJPAPaymentClassification;

@Entity
public class JPATimeCard {
	
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
	
	public int workingHourQty;
	
	@ManyToOne  	
	@JoinColumn(name="employeeId", referencedColumnName="employeeId", insertable = false, updatable = false)
	public HourlyJPAPaymentClassification hourlyJPAPaymentClassification;

	public JPATimeCard() {}
	public JPATimeCard(LocalDate date, int workingHoursQty) {
		id.date = date;
		this.workingHourQty = workingHoursQty;
	}
	
	public void connect(HourlyJPAPaymentClassification hourlyJPAPaymentClassification) {
		this.hourlyJPAPaymentClassification = hourlyJPAPaymentClassification;
		this.id.employeeId = hourlyJPAPaymentClassification.employeeId;
	}
	
}
