package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase.usecases.pay.send.interactor;

public class HoldMethodSendPayInteractor implements SendPayInteractor {

	@Override
	public void pay(int amount) {
		System.out.println("HoldPayInteractor.pay " + amount);
		// TODO Auto-generated method stub
	}


}
