package org.volkov_artiam.main.parser;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class PostfixConverterTest {

	
    @Test
    void isEmpty() {
        String input = "";
        PostfixConverter converter = new PostfixConverter();
        assertThrows( IllegalArgumentException.class, ()-> converter.convertToPostfix(input));
    }

    @Test
    void isNull() {
        String input = null;
        PostfixConverter converter = new PostfixConverter();
        assertThrows( NullPointerException.class, ()-> converter.convertToPostfix(input));
    }

    @Test
    void testConverterCaseOne() {
        String input = "123.0+123.123" ;
        PostfixConverter converter = new PostfixConverter();
        List<String> postfix = converter.convertToPostfix(input);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("123.0");
        expected.add("123.123");
        expected.add("+");

        assertEquals( expected, postfix );
    }

    @Test
    void testConverterCaseTwo() {
        String input = "123.0+123.123" ;
        PostfixConverter converter = new PostfixConverter();
        List<String> postfix = converter.convertToPostfix(input);
        
        ArrayList<String> expected = new ArrayList<>();
        expected.add("123.0");
        expected.add("123.123");
        expected.add("+");
        assertEquals( expected, postfix );
    }

    @Test
    void testConverterCaseThree() {
        String input = "10.0/100.0*10.0" ;
        PostfixConverter converter = new PostfixConverter();
        List<String> postfix = converter.convertToPostfix(input);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("10.0");
        expected.add("100.0");
        expected.add("/");
        expected.add("10.0");
        expected.add("*");
        assertEquals( expected, postfix );
    }
	
}