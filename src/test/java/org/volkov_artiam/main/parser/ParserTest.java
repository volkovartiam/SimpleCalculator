package org.volkov_artiam.main.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.volkov_artiam.operators.AllOperators;

class ParserTest {

	
    @Test
    void testParseWithoutUknownSymbolsCaseOne() {

        String input = "123.0+123.123" ;
        Parser parser = new Parser();
        parser.parse(input);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("123.0");
        expected.add("+");
        expected.add("123.123");

        assertEquals( expected, parser.getEqList());
        assertTrue(parser.getUknownsList().isEmpty());
    }

    @Test
    void testParseWithoutUknownSymbolsCaseTwo() {

        String input = "1.0-5.0" ;
        Parser parser = new Parser();
        parser.setOperatorsPatternsList(new AllOperators().getPatternsList() );
        parser.parse(input);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("1.0");
        expected.add("-");
        expected.add("5.0");

        assertEquals( expected, parser.getEqList());
        assertTrue(parser.getUknownsList().isEmpty());
    }

    @Test
    void testParseWithoutUknownSymbolsCaseThree() {

        String input = "3.7/15.0-4.0" ;
        Parser parser = new Parser();
        parser.setOperatorsPatternsList(new AllOperators().getPatternsList() );
        parser.parse(input);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("3.7");
        expected.add("/");
        expected.add("15.0");
        expected.add("-");
        expected.add("4.0");

        assertEquals( expected, parser.getEqList());
        assertTrue(parser.getUknownsList().isEmpty());
    }

    @Test
    void testParseWithoutUknownSymbolsCaseFour() {

        String input = "(45.0*45.0)" ;
        Parser parser = new Parser();
        parser.setOperatorsPatternsList(new AllOperators().getPatternsList() );
        parser.parse(input);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("(");
        expected.add("45.0");
        expected.add("*");
        expected.add("45.0");
        expected.add(")");

        assertEquals( expected, parser.getEqList());
        assertTrue(parser.getUknownsList().isEmpty());
    }

    @Test
    void testParseWithoutUknownSymbolsCaseFive() {

        String input = "sin(3.7*15.0)";
        Parser parser = new Parser();
        parser.setOperatorsPatternsList(new AllOperators().getPatternsList() );
        parser.parse(input);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("sin");
        expected.add("(");
        expected.add("3.7");
        expected.add("*");
        expected.add("15.0");
        expected.add(")");

        assertEquals( expected, parser.getEqList());
        assertTrue(parser.getUknownsList().isEmpty());
    }

    @Test
    void testParseWithUknownSymbolsCaseOne() {
        String input = "123.0..+123.123";
        Parser parser = new Parser();
        parser.parse(input);

        ArrayList<String> expectedOperators = new ArrayList<>();
        expectedOperators.add("123.0");
        expectedOperators.add("+");
        expectedOperators.add("123.123");

        ArrayList<String> expectedUknown = new ArrayList<>();
        expectedUknown.add("..");

        assertEquals(expectedOperators, parser.getEqList());
        assertEquals(expectedUknown, parser.getUknownsList() );
    }

    @Test
    void testParseWithUknownSymbolsCaseTwo() {
        String input = "1.0-5.0xx" ;
        Parser parser = new Parser();
        parser.setOperatorsPatternsList(new AllOperators().getPatternsList() );
        parser.parse(input);

        ArrayList<String> expectedOperators = new ArrayList<>();
        expectedOperators.add("1.0");
        expectedOperators.add("-");
        expectedOperators.add("5.0");
        ArrayList<String> expectedUknown = new ArrayList<>();
        expectedUknown.add("xx");

        assertEquals(expectedOperators, parser.getEqList());
        assertEquals(expectedUknown, parser.getUknownsList() );
    }


    @Test
    void testParseWithUknownSymbolsCaseThree() {
        String input = "3.7/==15.0-4.0" ;
        Parser parser = new Parser();
        parser.setOperatorsPatternsList(new AllOperators().getPatternsList() );
        parser.parse(input);

        ArrayList<String> expectedOperators = new ArrayList<>();
        expectedOperators.add("3.7");
        expectedOperators.add("/");
        expectedOperators.add("15.0");
        expectedOperators.add("-");
        expectedOperators.add("4.0");
        ArrayList<String> expectedUknown = new ArrayList<>();
        expectedUknown.add("==");

        System.out.println(parser.getUknownsList() );
        System.out.println(expectedUknown );
        
        assertEquals(expectedOperators, parser.getEqList());
        assertEquals(expectedUknown, parser.getUknownsList() );
    }


    @Test
    void testParseWithUknownSymbolsCaseFour() {

        String input = "u(x45.0c*45.0)" ;
        Parser parser = new Parser();
        parser.setOperatorsPatternsList(new AllOperators().getPatternsList() );
        parser.parse(input);

        ArrayList<String> expectedOperators = new ArrayList<>();
        expectedOperators.add("(");
        expectedOperators.add("45.0");
        expectedOperators.add("*");
        expectedOperators.add("45.0");
        expectedOperators.add(")");

        ArrayList<String> expectedUknown = new ArrayList<>();
        expectedUknown.add("u");
        expectedUknown.add("x");
        expectedUknown.add("c");

        assertEquals(expectedOperators, parser.getEqList());
        assertEquals(expectedUknown, parser.getUknownsList() );
    }


    @Test
    void testParseWithUknownSymbolsCaseFive() {

        ArrayList<String> expectedUknownFive = new ArrayList<>();
        expectedUknownFive.add("siin");

        String input = "siin(3.7*15.0)" ;
        Parser parser = new Parser();
        parser.setOperatorsPatternsList(new AllOperators().getPatternsList() );
        parser.parse(input);

        ArrayList<String> expectedOperators = new ArrayList<>();
        expectedOperators.add("(");
        expectedOperators.add("3.7");
        expectedOperators.add("*");
        expectedOperators.add("15.0");
        expectedOperators.add(")");

        ArrayList<String> expectedUknown = new ArrayList<>();
        expectedUknown.add("siin");

        assertEquals(expectedOperators, parser.getEqList());
        assertEquals(expectedUknown, parser.getUknownsList() );
    }



    @Test
    void isEmpty() {
        String input = "";
        Parser parser = new Parser();
        //assertThrows( IllegalArgumentException.class, ()-> parser.parse(input));
    }

    @Test
    void isNull() {
        String input = null;
        Parser parser = new Parser();
        assertThrows( NullPointerException.class, ()-> parser.parse(input));
    }

}