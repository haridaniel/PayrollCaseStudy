package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.paymentclassification.commissioned;

import java.time.LocalDate;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.model.paymentclassification.commissioned.JPASalesReceipt;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.Proxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.secondary.database.jpa.proxy.factory.AutoBindedProxy;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.paymenttype.SalesReceipt;

@AutoBindedProxy(JPASalesReceipt.class)
public class SalesReceiptProxy extends SalesReceipt implements Proxy<JPASalesReceipt>{

	private JPASalesReceipt jpaSalesReceipt;

	public SalesReceiptProxy(JPASalesReceipt jpaSalesReceipt) {
		this.jpaSalesReceipt = jpaSalesReceipt;
	}

	@Override
	public int getAmount() {
		return jpaSalesReceipt.amount;
	}

	@Override
	public LocalDate getDate() {
		return jpaSalesReceipt.id.date;
	}

	@Override
	public void setAmount(int amount) {
		jpaSalesReceipt.amount = amount;
	}

	@Override
	public void setDate(LocalDate date) {
		jpaSalesReceipt.id.date = date;
	}

	@Override
	public JPASalesReceipt getJPAObject() {
		return jpaSalesReceipt;
	}
}
