package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecasesboundary.requestresponse.request.Request;

public interface UseCase<R extends Request> {
	void execute(R request);
}