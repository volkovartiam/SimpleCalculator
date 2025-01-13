package org.volkov_artiam.main.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

class ParserTest2 {

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
    void testParseWithUknownSymbolsCaseOne() {

        String input = "123.0+x123.123" ;
        Parser parser = new Parser();
        parser.parse(input);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("123.0");
        expected.add("+");
        expected.add("123.123");

        ArrayList<String> expectedUknown = new ArrayList<>();
        expectedUknown.add("x");

        assertEquals( expected, parser.getEqList());
        assertEquals(expectedUknown, parser.getUknownsList() );
    }
	
    @Test
    void testSetOperatorPatterns() {

        Parser parser = new Parser();
    	List<String> operators = new ArrayList<>(List.of("\\+", "\\-", "\\*", "\\/", "\\d+\\.\\d+"));
    	parser.setOperatorsPatternsList(operators);
    	String input = "123.00+123.00/89.0*1.0-3.0";
    	parser.parse(input);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("123.00");
        expected.add("+");
        expected.add("123.00");
        expected.add("/");
        expected.add("89.0");
        expected.add("*");
        expected.add("1.0");
        expected.add("-");
        expected.add("3.0");

        ArrayList<String> expectedUknown = new ArrayList<>();

        assertEquals( expected, parser.getEqList());
        assertEquals(expectedUknown, parser.getUknownsList() );
    	
    }
    
}
