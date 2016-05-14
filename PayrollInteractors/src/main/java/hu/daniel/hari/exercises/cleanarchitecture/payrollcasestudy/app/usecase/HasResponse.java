package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.Response;

@Deprecated
public interface HasResponse<T extends Response> {
	T getResponse();
}