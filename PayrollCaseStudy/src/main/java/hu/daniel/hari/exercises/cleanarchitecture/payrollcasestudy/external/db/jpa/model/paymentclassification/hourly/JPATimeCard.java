package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.hourly;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.HourlyJPAPaymentClassification;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class JPATimeCard {
	
	public JPATimeCard(LocalDate date, int workingHoursQty) {
		id.date = date;
		workingHourQty = workingHoursQty;
	}
	
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
	
	@ManyToOne  	
	@JoinColumn(name="employeeId", referencedColumnName="employeeId", insertable = false, updatable = false)
	public HourlyJPAPaymentClassification hourlyJPAPaymentClassification;
	
	public int workingHourQty;
	
}
