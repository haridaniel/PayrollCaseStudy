package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.usecase;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.ui.requestresponse.response.Response;

public interface HasResponse<T extends Response> {
	T getResponse();
}