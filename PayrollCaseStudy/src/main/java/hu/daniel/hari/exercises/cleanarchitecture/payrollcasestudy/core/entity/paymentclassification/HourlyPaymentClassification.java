package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;

import java.util.Collection;

public abstract class HourlyPaymentClassification extends PaymentClassification {

	public abstract void setHourlyWage(int hourlyWage);
	public abstract int getHourlyWage();

	public abstract void addTimeCard(TimeCard timeCard);

	@Override
	public int calculateAmount(DateInterval payInterval) {
		return calculateAmountIteratingOnTimeCardsInInterval(payInterval);
	}

	private int calculateAmountIteratingOnTimeCardsInInterval(DateInterval payInterval) {
		int sumAmount = 0;
		Collection<TimeCard> timeCards = getTimeCardsIn(payInterval);
		for (TimeCard timeCard : timeCards) {
			sumAmount += calculateAmount(timeCard);
		}
		return sumAmount;
	}

	public abstract Collection<TimeCard> getTimeCardsIn(DateInterval payInterval);

	private int calculateAmount(TimeCard timeCard) {
		return timeCard.getWorkingHourQty() * getHourlyWage();
	}

}
