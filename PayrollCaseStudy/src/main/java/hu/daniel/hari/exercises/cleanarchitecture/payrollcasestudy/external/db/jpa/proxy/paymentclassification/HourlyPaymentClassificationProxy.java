package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.HourlyPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.core.entity.paymentclassification.TimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.dao.TimeCardDao;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.HourlyJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.JPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.model.paymentclassification.hourly.JPATimeCard;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.external.db.jpa.proxy.paymentclassification.hourly.TimeCardProxy;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;

public class HourlyPaymentClassificationProxy extends HourlyPaymentClassification implements PaymentClassificationProxy {

	private HourlyJPAPaymentClassification hourlyJPAPaymentClassification;
	
	@Inject
	private TimeCardDao timeCardDao;

	@Inject
	public HourlyPaymentClassificationProxy(@Assisted HourlyJPAPaymentClassification hourlyJPAPaymentClassification) {
		this.hourlyJPAPaymentClassification = hourlyJPAPaymentClassification;
	}
	
	@Override
	public int getHourlyWage() {
		return hourlyJPAPaymentClassification.getHourlyWage();
	}
	
	@Override
	public void setHourlyWage(int hourlyWage) {
		hourlyJPAPaymentClassification.setHourlyWage(hourlyWage);
	}
	
	@Override
	public void addTimeCard(TimeCard timeCard) {
		hourlyJPAPaymentClassification.addJPATimeCard(((TimeCardProxy) timeCard).getJPATimeCard());
	}

	@Override
	public Collection<TimeCard> getTimeCardsIn(DateInterval dateInterval) {
		return convert(timeCardDao.findTimeCardsIn(dateInterval));
	}

	private List<TimeCard> convert(Collection<JPATimeCard> jpaTimeCards) {
		return jpaTimeCards
				.stream()
				.map(jpaTimeCard -> convert(jpaTimeCard))
				.collect(Collectors.toList());
	}

	private TimeCardProxy convert(JPATimeCard jpaTimeCard) {
		return new TimeCardProxy(jpaTimeCard);
	}

	@Override
	public JPAPaymentClassification getJPAPaymentClassification() {
		return hourlyJPAPaymentClassification;
	}
	
	public interface HourlyPaymentClassificationProxyFactory {
		HourlyPaymentClassificationProxy create(HourlyJPAPaymentClassification hourlyJPAPaymentClassification);
	}

}
