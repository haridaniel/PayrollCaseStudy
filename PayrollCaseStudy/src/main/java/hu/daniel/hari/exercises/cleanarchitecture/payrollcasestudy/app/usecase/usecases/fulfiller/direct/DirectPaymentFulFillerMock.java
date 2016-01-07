package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.fulfiller.direct;

public class DirectPaymentFulFillerMock implements DirectPaymentFulFiller {

	@Override
	public void pay(int amount) {
		System.out.println("Paying" + amount);
	}

}
