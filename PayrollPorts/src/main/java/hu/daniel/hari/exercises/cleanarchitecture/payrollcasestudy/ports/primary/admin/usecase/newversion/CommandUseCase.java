package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.newversion;

public interface CommandUseCase<T> extends UseCase {
	public void execute(T request);	
}
