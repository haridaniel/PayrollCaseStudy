package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.temp.usecase.version1b;

public interface CommandUseCase<T> extends UseCase {
	public void execute(T request);	
}
