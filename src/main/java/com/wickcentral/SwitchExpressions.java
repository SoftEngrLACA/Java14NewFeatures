package com.wickcentral;

import java.time.Month;

public class SwitchExpressions {

	public static void main(String[] args) {

		System.out.println("Explore Switch Expressions");

		try {

			// Use an array containing the constants of this enum type Month, in the order they are declared
			Month months[] = Month.values();
			for (Month month : months) {
				useArrowNotationAndOptionalCurlyBraces(month);
			}

			
			
		} catch (Exception e) {
			System.err.println("Exception: " + e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			System.out.println("... SwitchExpressions DONE!");
		}

	}

	/**
	 * Use the arrow notation and return a value, skip yield keyword 
	 * Since enums are used, can skip the default case
	 * Or an error is shown: A Switch expression should cover all possible values
	 * @param month Month
	 * @return String quarter
	 */
	public static String useArrowNotationForQuarter(Month month) {

		return switch (month) {
			case JANUARY, FEBRUARY, MARCH -> "First quarter";
			case APRIL, MAY, JUNE -> "Second quarter";
			case JULY, AUGUST, SEPTEMBER -> "Third quarter";
			case OCTOBER, NOVEMBER, DECEMBER  -> "Fourth quarter";
		};

		}
		
	public static void useArrowNotationAndOptionalCurlyBraces(Month month) {

		switch (month) {
			case JANUARY, FEBRUARY, MARCH -> System.out.println("First quarter for month " + month.name());
			case APRIL, MAY, JUNE -> System.out.println("Second quarter for month " + month.name());
			case JULY, AUGUST, SEPTEMBER -> System.out.println("Third quarter for month " + month.name());
			// use curly braces when more than one line is needed
			case OCTOBER, NOVEMBER, DECEMBER -> {
				System.out.println("Fourth quarter for month " + month.name());
			}
		}
	}
	
	/**
	 * Uses yield: it is not a new keyword but a restricted identifier
	 * It can only exist at the end of the case branch
	 * yield statement transfers control by causing an enclosing switch expression to produce a specified value
	 * @param month
	 * @return
	 */
	public static String yieldInsteadOfBreakToReturnValues(Month month) throws NullPointerException {
		return switch (month) {
		
			case DECEMBER, JANUARY, FEBRUARY -> {
				 System.out.println("It's Winter!");
				 yield "Winter";
			}
			case MARCH, APRIL, MAY ->  {
				 System.out.println("It's Spring!");
				 yield "Spring";
			}
			case JUNE, JULY, AUGUST  ->  {
				 System.out.println("It's Summer!");
				 yield "Summer";
			}
			case SEPTEMBER, OCTOBER, NOVEMBER ->  {
				 System.out.println("It's Autumn!");
				 yield "Autumn";
			}
		
		};
		
	}
	
	/**
	 * Enforce exhaustive cases where a compilation error would be thrown if all the input cases aren’t covered
	 * Default case is needed since no enums are used
	 * When using the colon (:) operator, need to use yield restricted identifier
	 * Mixing of different kinds of case statements '->' and  ':' is not allowed within a switch
	 * @param num
	 * @return
	 */
	public static String defaultException(int num) {
		return switch (num) {
		
			case 1, 2, 3 : yield "one, two or three";
			
			default : {
				
				if (num <= 0) yield "Number is too low";
				else throw new IllegalArgumentException("Number = " + num);
			}
		
		};
		
	}
	

}
