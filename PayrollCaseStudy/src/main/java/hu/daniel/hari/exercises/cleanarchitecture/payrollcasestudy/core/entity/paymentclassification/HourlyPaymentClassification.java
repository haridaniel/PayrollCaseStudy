package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.exception.NotImplementedException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class HourlyPaymentClassification extends PaymentClassification {

	private int hourlyWage;

	private Map<LocalDate, TimeCard> timeCardsByDate = new HashMap<>();

	public HourlyPaymentClassification(int hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public int getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(int hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public TimeCard getTimeCard(LocalDate date) {
		return timeCardsByDate.get(date);
	}

	public void addTimeCard(TimeCard timeCard) {
		timeCardsByDate.put(timeCard.date, timeCard);
	}

	@Override
	public int calculatePay(LocalDate payDate) {
		int sumAmount = 0;
		for (LocalDate timeCardDate : timeCardsByDate.keySet()) {
			if(isInPayPeriod(timeCardDate, payDate))
				sumAmount += calculateAmount(timeCardsByDate.get(timeCardDate));
		}
		return sumAmount;
	}
	

	private int calculateAmount(TimeCard timeCard) {
		return timeCard.workingHourQty * hourlyWage;
	}
	
	@Deprecated
	private boolean isInPayPeriod(LocalDate timeCardDate, LocalDate payDate) {
//		int daysToSubtractMAGIC = 6;
		int daysToSubtractMAGIC = 100;
		LocalDate periodEndDateInclusive = payDate;
		LocalDate periodStartDateInclusive = payDate.minusDays(daysToSubtractMAGIC);
		
		boolean isBetween = 
				(timeCardDate.isAfter(periodStartDateInclusive) || timeCardDate.isEqual(periodStartDateInclusive)) 
					&& 
				(timeCardDate.isBefore(periodEndDateInclusive) || timeCardDate.isEqual(periodEndDateInclusive));
		
		return isBetween;
	}
	
}
