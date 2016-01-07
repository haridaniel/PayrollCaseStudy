package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.affiliation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.Test;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.usecases.entity.DateInterval;

public class UnionMemberAffiliationTest {
	private static final DateInterval INTERVAL_HAS_4_FRIDAYS = DateInterval.of(LocalDate.of(2015, 12, 01), LocalDate.of(2015, 12, 31));

	@Test
	public void testCalculateDeductionsAmount() {
		UnionMemberAffiliation unionMemberAffiliation = mock(UnionMemberAffiliation.class, CALLS_REAL_METHODS);
		when(unionMemberAffiliation.getWeeklyDueAmount()).thenReturn(25);
		
		assertThat(unionMemberAffiliation.calculateDeductionsAmount(INTERVAL_HAS_4_FRIDAYS), is(4 * 25));
		
	}

}
