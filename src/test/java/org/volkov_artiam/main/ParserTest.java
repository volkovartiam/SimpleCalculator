package org.volkov_artiam.main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void testParseWithoutUknownSymbolsCaseOne() {

        String input = "123+123.123" ;
        Parser parser = new Parser();
        parser.parse(input);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("123");
        expected.add("+");
        expected.add("123.123");

        assertEquals( expected, parser.getEquationList());
        assertTrue(parser.unknownSymbolsList.isEmpty());
    }

    @Test
    void testParseWithoutUknownSymbolsCaseTwo() {

        String input = "1-5" ;
        Parser parser = new Parser();
        parser.parse(input);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("-");
        expected.add("5");

        assertEquals( expected, parser.getEquationList());
        assertTrue(parser.unknownSymbolsList.isEmpty());
    }

    @Test
    void testParseWithoutUknownSymbolsCaseThree() {

        String input = "3.7/15-4" ;
        Parser parser = new Parser();
        parser.parse(input);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("3.7");
        expected.add("/");
        expected.add("15");
        expected.add("-");
        expected.add("4");

        assertEquals( expected, parser.getEquationList());
        assertTrue(parser.unknownSymbolsList.isEmpty());
    }

    @Test
    void testParseWithoutUknownSymbolsCaseFour() {

        String input = "(45*45)" ;
        Parser parser = new Parser();
        parser.parse(input);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("(");
        expected.add("45");
        expected.add("*");
        expected.add("45");
        expected.add(")");

        assertEquals( expected, parser.getEquationList());
        assertTrue(parser.unknownSymbolsList.isEmpty());
    }

    @Test
    void testParseWithoutUknownSymbolsCaseFive() {

        String input = "sin(3.7*15)";
        Parser parser = new Parser();
        parser.parse(input);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("sin");
        expected.add("(");
        expected.add("3.7");
        expected.add("*");
        expected.add("15");
        expected.add(")");

        assertEquals( expected, parser.getEquationList());
        assertTrue(parser.unknownSymbolsList.isEmpty());
    }

    @Test
    void testParseWithUknownSymbolsCaseOne() {
        String input = "123..+123.123";
        Parser parser = new Parser();
        parser.parse(input);

        ArrayList<String> expectedOperators = new ArrayList<>();
        expectedOperators.add("123");
        expectedOperators.add("+");
        expectedOperators.add("123.123");

        ArrayList<String> expectedUknown = new ArrayList<>();
        expectedUknown.add("..");

        assertEquals(expectedOperators, parser.getEquationList());
        assertEquals(expectedUknown, parser.getUknownSymbolsList() );
    }

    @Test
    void testParseWithUknownSymbolsCaseTwo() {
        String input = "1-5xx" ;
        Parser parser = new Parser();
        parser.parse(input);

        ArrayList<String> expectedOperators = new ArrayList<>();
        expectedOperators.add("1");
        expectedOperators.add("-");
        expectedOperators.add("5");
        ArrayList<String> expectedUknown = new ArrayList<>();
        expectedUknown.add("xx");

        assertEquals(expectedOperators, parser.getEquationList());
        assertEquals(expectedUknown, parser.getUknownSymbolsList() );
    }


    @Test
    void testParseWithUknownSymbolsCaseThree() {
        String input = "3.7/==15-4" ;
        Parser parser = new Parser();
        parser.parse(input);

        ArrayList<String> expectedOperators = new ArrayList<>();
        expectedOperators.add("3.7");
        expectedOperators.add("/");
        expectedOperators.add("15");
        expectedOperators.add("-");
        expectedOperators.add("4");
        ArrayList<String> expectedUknown = new ArrayList<>();
        expectedUknown.add("==");

        assertEquals(expectedOperators, parser.getEquationList());
        assertEquals(expectedUknown, parser.getUknownSymbolsList() );
    }


    @Test
    void testParseWithUknownSymbolsCaseFour() {

        String input = "u(x45c*45)" ;
        Parser parser = new Parser();
        parser.parse(input);

        ArrayList<String> expectedOperators = new ArrayList<>();
        expectedOperators.add("(");
        expectedOperators.add("45");
        expectedOperators.add("*");
        expectedOperators.add("45");
        expectedOperators.add(")");

        ArrayList<String> expectedUknown = new ArrayList<>();
        expectedUknown.add("u");
        expectedUknown.add("x");
        expectedUknown.add("c");

        assertEquals(expectedOperators, parser.getEquationList());
        assertEquals(expectedUknown, parser.getUknownSymbolsList() );
    }


    @Test
    void testParseWithUknownSymbolsCaseFive() {

        ArrayList<String> expectedUknownFive = new ArrayList<>();
        expectedUknownFive.add("siin");

        String input = "siin(3.7*15)" ;
        Parser parser = new Parser();
        parser.parse(input);

        ArrayList<String> expectedOperators = new ArrayList<>();
        expectedOperators.add("(");
        expectedOperators.add("3.7");
        expectedOperators.add("*");
        expectedOperators.add("15");
        expectedOperators.add(")");

        ArrayList<String> expectedUknown = new ArrayList<>();
        expectedUknown.add("siin");

        assertEquals(expectedOperators, parser.getEquationList());
        assertEquals(expectedUknown, parser.getUknownSymbolsList() );
    }



    @Test
    void isEmpty() {
        String input = "";
        Parser parser = new Parser();
        assertThrows( IllegalArgumentException.class, ()-> parser.parse(input));
    }

    @Test
    void isNull() {
        String input = null;
        Parser parser = new Parser();
        assertThrows( NullPointerException.class, ()-> parser.parse(input));
    }

}