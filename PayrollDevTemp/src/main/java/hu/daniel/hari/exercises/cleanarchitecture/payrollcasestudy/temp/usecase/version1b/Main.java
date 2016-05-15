package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.temp.usecase.version1b;

import java.time.LocalDate;
import java.util.List;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.DeleteEmployeeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.request.PayListRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.PayListResponse;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.response.PayListResponse.PayListResponseItem;

public class Main {
	public static void main(String[] args) {
		UseCaseFactories useCaseFactories = new UseCaseFactoriesImpl();
		DeleteEmployeeUseCaseFactory deleteEmployeeUseCaseFactory = useCaseFactories;
		PayListUseCaseFactory payListUseCaseFactory = useCaseFactories;
		
		deleteEmployeeUseCaseFactory.deleteEmployeeUseCase().execute(new DeleteEmployeeRequest(10));
		
		PayListResponse payListResponse = payListUseCaseFactory.payListUseCase().execute(new PayListRequest(LocalDate.now()));
		
		
	}
}
