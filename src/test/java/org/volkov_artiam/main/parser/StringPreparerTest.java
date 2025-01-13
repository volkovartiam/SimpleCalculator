package org.volkov_artiam.main.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

class StringPreparerTest {

	@Test
	void testSimpleNumber() {
		String input = "1";
 		String prepared = new StringPreparer().convert(input);
 		String expected = "1.0";
 		assertEquals( expected, prepared );
	}

	@Test
	void testSimpleNumber_2() {
		String input = "112";
 		String prepared = new StringPreparer().convert(input);
 		String expected = "112.0";
 		assertEquals( expected, prepared );
	}

	@Test
	void testSimpleNumberWithDot() {
		String input = "2.";
 		String prepared = new StringPreparer().convert(input);
 		String expected = "2.0";
 		assertEquals( expected, prepared );
	}

	@Test
	void testSimpleNumberWithDot_2() {
		String input = "222.";
 		String prepared = new StringPreparer().convert(input);
 		String expected = "222.0";
 		assertEquals( expected, prepared );
	}
	
	@Test
	void testOperatorsNumberWithoutDots() {
		String input = "+-2";
 		String prepared = new StringPreparer().convert(input);
 		String expected = "+-2.0";
 		assertEquals( expected, prepared );
	}
	
	@Test
	void testOperatorsWithNumberDot() {
		String input = "+-2.";
 		String prepared = new StringPreparer().convert(input);
 		String expected = "+-2.0";
 		assertEquals( expected, prepared );
	}
	
	@Test
	void testOperatorsWithNumbersSimpleExpression() {
		String input = "311-2+3";
 		String prepared = new StringPreparer().convert(input);
 		String expected = "311.0-2.0+3.0";
 		assertEquals( expected, prepared );
	}
	
	@Test
	void testOperatorsWithNumbersWithDotsExpression() {
		String input = "123.+123.123+12.0";
 		String prepared = new StringPreparer().convert(input);
 		String expected = "123.0+123.123+12.0";
 		assertEquals( expected, prepared );
	}
	
	@Test
	void testsExpressions() {
		String input = "-1.0+20.+3+2";
 		String prepared = new StringPreparer().convert(input);
 		String expected = "-1.0+20.0+3.0+2.0";
 		assertEquals( expected, prepared );
	}
	
	@Test
	void testsExpressions_2() {
		String input = "123.+123.123";
 		String prepared = new StringPreparer().convert(input);
 		String expected = "123.0+123.123";
 		assertEquals( expected, prepared );
	}
	
	@Test
	void testsExpressions_3() {
		String[] input = {"10.023", "+10", "10+", "10.", "21+3.0", "21.00+10"};
		String[] expected = {"10.023", "+10.0", "10.0+", "10.0", "21.0+3.0", "21.00+10.0"};
		String[] prepared = new String[expected.length];
		
		StringPreparer preparer = new StringPreparer();
		for (int i = 0; i < prepared.length; i++) {
			prepared[i] = preparer.convert(input[i]);
		}
		ArrayList<String> expectedList = new ArrayList<>(Arrays.asList(expected));
		ArrayList<String> preparedList = new ArrayList<>(Arrays.asList(prepared));

		assertEquals( expectedList, preparedList );
	}
	
	@Test
	void testsExpressions_4() {
		String[] input = { "123.+123.123+12.0", "10.0+20","1.0+20.", "1.0+20.00"};
		String[] expected = {"123.0+123.123+12.0", "10.0+20.0","1.0+20.0", "1.0+20.00"};
		String[] prepared = new String[expected.length];
		
		StringPreparer preparer = new StringPreparer();
		for (int i = 0; i < prepared.length; i++) {
			prepared[i] = preparer.convert(input[i]);
		}
		ArrayList<String> expectedList = new ArrayList<>(Arrays.asList(expected));
		ArrayList<String> preparedList = new ArrayList<>(Arrays.asList(prepared));
		assertEquals( expectedList, preparedList );
	}
	
	
	@Test
	void testsExpressions_5() {
		String[] input = {"21+3.0", "21+3.", "21+3", "21+3+", "+3.0+123.+", "21+3.+","21+3.0", "21+3.0+", "21+3.0+1" };
		String[] expected = {"21.0+3.0", "21.0+3.0", "21.0+3.0", "21.0+3.0+", "+3.0+123.0+", "21.0+3.0+","21.0+3.0", "21.0+3.0+", "21.0+3.0+1.0" };
		String[] prepared = new String[expected.length];
		
		StringPreparer preparer = new StringPreparer();
		for (int i = 0; i < prepared.length; i++) {
			prepared[i] = preparer.convert(input[i]);
		}
		ArrayList<String> expectedList = new ArrayList<>(Arrays.asList(expected));
		ArrayList<String> preparedList = new ArrayList<>(Arrays.asList(prepared));
		assertEquals( expectedList, preparedList );
	}
	 
	@Test
	void testsExpressions_6() {
		String[] input = { "-123.+123.123+12.0", "10.0+20","1.0+20.",	"1.0+20.00", "-21+3.+", "3.", "3.01", "-3", "3+", "3+2", };
		String[] expected = { "-123.0+123.123+12.0", "10.0+20.0","1.0+20.0",	"1.0+20.00", "-21.0+3.0+", "3.0", "3.01", "-3.0", "3.0+", "3.0+2.0", };
		String[] prepared = new String[expected.length];
		
		StringPreparer preparer = new StringPreparer();
		for (int i = 0; i < prepared.length; i++) {
			prepared[i] = preparer.convert(input[i]);
		}
		ArrayList<String> expectedList = new ArrayList<>(Arrays.asList(expected));
		ArrayList<String> preparedList = new ArrayList<>(Arrays.asList(prepared));
		assertEquals( expectedList, preparedList );
	}
	  
	 

	

	
	
}
