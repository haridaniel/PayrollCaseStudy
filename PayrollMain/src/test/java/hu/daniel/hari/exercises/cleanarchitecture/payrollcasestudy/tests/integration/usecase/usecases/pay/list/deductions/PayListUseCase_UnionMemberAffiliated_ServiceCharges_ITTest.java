package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.tests.integration.usecase.usecases.pay.list.deductions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.app.entity.PayCheck;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.request.AddServiceChargeRequest;
import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.ports.primary.admin.usecase.response.PayListResponse.PayListResponseItem;

public class PayListUseCase_UnionMemberAffiliated_ServiceCharges_ITTest extends PayListUseCase_AbstractUnionMemberAffiliated_ITTest {
	private static final LocalDate FIRST_DAY_OF_A_MONTH = LocalDate.of(2015, 12, 01);
	private static final LocalDate LAST_DAY_OF_A_MONTH = LocalDate.of(2015, 12, 31);
	private static final LocalDate A_SALARIED_EMPLOYEE_PAYDAY = LAST_DAY_OF_A_MONTH;

	private abstract class Case {
		List<AddServiceChargeRequest> serviceCharges;
		int thenPayCheckDeductionsAmount;
	}
	
	@Test
	public void testNoServiceCharge() throws Exception {
		givenWhenThen(new Case() {{
			serviceCharges = Collections.emptyList();
			thenPayCheckDeductionsAmount = 0;
		}});
	}

	@Test
	public void testOneServiceCharge() throws Exception {
		givenWhenThen(new Case() {{
			serviceCharges = new ArrayList<AddServiceChargeRequest>() {{
				add(new AddServiceChargeRequest(unionMemberId, A_SALARIED_EMPLOYEE_PAYDAY, 50));
			}};
			thenPayCheckDeductionsAmount = (50);
		}});
	}

	@Test
	public void testTwoServiceCharges_AtBordersOfPaymentPeriod() throws Exception {
		givenWhenThen(new Case() {{
			serviceCharges = new ArrayList<AddServiceChargeRequest>() {{
				add(new AddServiceChargeRequest(unionMemberId, FIRST_DAY_OF_A_MONTH, 40));
				add(new AddServiceChargeRequest(unionMemberId, A_SALARIED_EMPLOYEE_PAYDAY, 50));
			}};
			thenPayCheckDeductionsAmount = (40 + 50);
		}});
	}
	
	@Test
	public void testThreeServiceCharges_SpanningThreePayPeriods_ThenShouldIgnoreOtherPeriods() throws Exception {
		givenWhenThen(new Case() {{
			serviceCharges = new ArrayList<AddServiceChargeRequest>() {{
				add(new AddServiceChargeRequest(unionMemberId, FIRST_DAY_OF_A_MONTH.minusDays(1), 10));			//In previous pay period, should be IGNORED  
				add(new AddServiceChargeRequest(unionMemberId, A_SALARIED_EMPLOYEE_PAYDAY, 20));				//In this pay period                         
				add(new AddServiceChargeRequest(unionMemberId, A_SALARIED_EMPLOYEE_PAYDAY.plusDays(1), 30));	//In next pay period, should be IGNORED      
			}};
			thenPayCheckDeductionsAmount = (20);
		}});
	}
	
	private void givenWhenThen(Case theCase) {
		givenASalariedEmployee_WithUnionMembershipAffiliation(0);
		givenServiceCharges(theCase.serviceCharges);
		
		Collection<PayListResponseItem> payChecks = whenGeneratePayUseCaseExecuted(A_SALARIED_EMPLOYEE_PAYDAY);
		
		thenPayCheckDeductionsAmount_ShouldBe(payChecks, theCase.thenPayCheckDeductionsAmount);
	}

	private void givenServiceCharges(List<AddServiceChargeRequest> serviceCharges) {
		for (AddServiceChargeRequest addServiceChargeRequest : serviceCharges) {
			useCaseFactories.addServiceChargeUseCase().execute(addServiceChargeRequest);
		}
	}
	
	public PayListUseCase_UnionMemberAffiliated_ServiceCharges_ITTest(TestDatabaseProvider testDatabaseProvider) {
		super(testDatabaseProvider);
	}

}
