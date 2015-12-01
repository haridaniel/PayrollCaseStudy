package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HourlyPaymentClassification extends PaymentClassification {

	public int hourlyRate;

	private Map<LocalDate, TimeCard> timeCardsByDate = new HashMap<>();

	public HourlyPaymentClassification(int hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public TimeCard getTimeCard(LocalDate date) {
		return timeCardsByDate.get(date);
	}

	public void addTimeCard(TimeCard timeCard) {
		timeCardsByDate.put(timeCard.date, timeCard);
	}

}
