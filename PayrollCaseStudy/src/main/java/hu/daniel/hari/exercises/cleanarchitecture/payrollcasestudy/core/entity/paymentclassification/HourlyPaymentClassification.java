package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

import java.time.LocalDate;
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
	public int calculateAmount(DateInterval payInterval) {
		return calculatePayIteratingOnAllTimeCards(payInterval);
	}

	// TODO: REDESIGN. CPU Resource leak. Get timecards for a period!
	@Deprecated
	private int calculatePayIteratingOnAllTimeCards(DateInterval payInterval) {
		int sumAmount = 0;
		for (LocalDate timeCardDate : timeCardsByDate.keySet()) {
			if (payInterval.isBetweenInclusive(timeCardDate)) {
				TimeCard timeCard = timeCardsByDate.get(timeCardDate);
				sumAmount += calculateAmount(timeCard);
			}
		}
		return sumAmount;
	}

	private int calculateAmount(TimeCard timeCard) {
		return timeCard.workingHourQty * hourlyWage;
	}

}
