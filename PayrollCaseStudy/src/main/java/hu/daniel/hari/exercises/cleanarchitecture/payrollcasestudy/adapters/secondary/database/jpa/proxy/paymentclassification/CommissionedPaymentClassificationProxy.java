package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.paymentclassification;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.inject.Inject;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.dao.JPASalesReceiptDao;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.CommissionedJPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.JPAPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.commissioned.JPASalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.ProxyFactory;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.paymentclassification.commissioned.SalesReceiptProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.util.autobind.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.DateInterval;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.CommissionedPaymentClassification;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymentclassification.SalesReceipt;

@AutoBindedProxy(CommissionedJPAPaymentClassification.class)
public class CommissionedPaymentClassificationProxy extends CommissionedPaymentClassification implements PaymentClassificationProxy {

	private CommissionedJPAPaymentClassification commissionedJPAPaymentClassification;

	@Inject private JPASalesReceiptDao salesReceiptDao;
	@Inject private ProxyFactory proxyFactory;
	
	@Inject
	public CommissionedPaymentClassificationProxy(CommissionedJPAPaymentClassification commissionedJPAPaymentClassification) {
		this.commissionedJPAPaymentClassification = commissionedJPAPaymentClassification;
	}

	@Override
	public int getBiWeeklyBaseSalary() {
		return commissionedJPAPaymentClassification.getBiWeeklyBaseSalary();
	}

	@Override
	public double getCommissionRate() {
		return commissionedJPAPaymentClassification.getCommissionRate();
	}

	@Override
	public void addSalesReceipt(SalesReceipt salesReceipt) {
		commissionedJPAPaymentClassification.addJPASalesReceipt(((SalesReceiptProxy) salesReceipt).getJPAObject());
	}

	@Override
	public Collection<SalesReceipt> getSalesReceiptsIn(DateInterval dateInterval) {
		return proxyAll(salesReceiptDao.findJPASalesReceiptsIn(dateInterval));
	}

	private Collection<SalesReceipt> proxyAll(Collection<JPASalesReceipt> jpaSalesReceipts) {
		return jpaSalesReceipts
				.stream()
				.map(jpaSalesReceipt -> proxy(jpaSalesReceipt))
				.collect(Collectors.toList());
	}

	private SalesReceipt proxy(JPASalesReceipt jpaSalesReceipt) {
		return proxyFactory.create(SalesReceiptProxy.class, jpaSalesReceipt);
	}

	@Override
	public JPAPaymentClassification getJPAObject() {
		return commissionedJPAPaymentClassification;
	}

}
