package test.java.com.wickcentral;

import java.time.Month;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.java.com.wickcentral.SwitchExpressions;

public class TestSwitchExpressions {
	
	static Month months[];
	
	@BeforeAll
	static void beforeAll() {
		// Use an array containing the constants of this enum type Month, in the order they are declared
		months = Month.values();
	}

	// disable a test: will not be run
	//@Disabled
	@DisplayName("Output All Quarters And Months")
	@Test
	void outputQuarterAndmonth() {
		for (Month month : months) {
			SwitchExpressions.useArrowNotationAndOptionalCurlyBraces(month);
		}
	}

	@DisplayName("Test the Quarter corresponding to a Month")
	@Test
	void testMonthAndQuarter() {
		
		Assertions.assertEquals("First quarter", SwitchExpressions.useArrowNotationForQuarter(Month.JANUARY));
		Assertions.assertEquals("First quarter", SwitchExpressions.useArrowNotationForQuarter(Month.FEBRUARY));
		Assertions.assertEquals("First quarter", SwitchExpressions.useArrowNotationForQuarter(Month.MARCH));
		
		Assertions.assertEquals("Second quarter", SwitchExpressions.useArrowNotationForQuarter(Month.APRIL));
		Assertions.assertEquals("Second quarter", SwitchExpressions.useArrowNotationForQuarter(Month.MAY));
		Assertions.assertEquals("Second quarter", SwitchExpressions.useArrowNotationForQuarter(Month.JUNE));
		
		Assertions.assertEquals("Third quarter", SwitchExpressions.useArrowNotationForQuarter(Month.JULY));
		Assertions.assertEquals("Third quarter", SwitchExpressions.useArrowNotationForQuarter(Month.AUGUST));
		Assertions.assertEquals("Third quarter", SwitchExpressions.useArrowNotationForQuarter(Month.SEPTEMBER));
		
		Assertions.assertEquals("Fourth quarter", SwitchExpressions.useArrowNotationForQuarter(Month.OCTOBER));
		Assertions.assertEquals("Fourth quarter", SwitchExpressions.useArrowNotationForQuarter(Month.NOVEMBER));
		Assertions.assertEquals("Fourth quarter", SwitchExpressions.useArrowNotationForQuarter(Month.DECEMBER));
		
		// Example of Failing a Test
		Assertions.assertEquals("Second quarter", SwitchExpressions.useArrowNotationForQuarter(Month.MARCH));
	}

	@DisplayName("Test yield instead of break to return values")
	@Test
	void testYieldInsteadOfBreakToReturnValues() {
		for (Month month : months) {
			SwitchExpressions.yieldInsteadOfBreakToReturnValues(month);
		}

	}

	@DisplayName("Test default exception")
	@Test
	void testDefaultException() {
		
		Assertions.assertEquals("one, two or three", SwitchExpressions.defaultException(1));
		Assertions.assertEquals("one, two or three", SwitchExpressions.defaultException(2));
		Assertions.assertEquals("one, two or three", SwitchExpressions.defaultException(3));
		
		Assertions.assertEquals("Number is too low", SwitchExpressions.defaultException(0));
		Assertions.assertEquals("Number is too low", SwitchExpressions.defaultException(-200));
		
		// Pass Exception Test
		Assertions.assertThrows(IllegalArgumentException.class, 
						() -> { SwitchExpressions.defaultException(5); } );
				
		// Fail Exception Test: expect IllegalArgumentException
		Assertions.assertThrows(NullPointerException.class, 
				() -> { SwitchExpressions.defaultException(5); }
				, "Expected IllegalArgumentException!" );

	}

	@DisplayName("Test other exceptions")
	@Test
	void testOtherExceptions() {
		
		// Test Exception thrown
		Assertions.assertThrows(IllegalArgumentException.class, 
						() -> { SwitchExpressions.yieldInsteadOfBreakToReturnValues(null); } 
						, "Expected IllegalArgumentException!" );
		
		// Just throw exception
		//SwitchExpressions.yieldInsteadOfBreakToReturnValues(null);
		
	}
	
	
	@AfterAll
	static void afterAll() {
		months = null;
	}

}
