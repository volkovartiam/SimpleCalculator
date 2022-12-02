package org.volkov_artiam.main;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class ConverterTest {

    @Test
    void isEmpty() {
        String input = "";
        Converter converter = new Converter();
        assertThrows( IllegalArgumentException.class, ()-> converter.convertToPostFix(input));
    }

    @Test
    void isNull() {
        String input = null;
        Converter converter = new Converter();
        assertThrows( NullPointerException.class, ()-> converter.convertToPostFix(input));
    }

    @Test
    void testConverterCaseOne() {
        String input = "123+123.123" ;
        Converter converter = new Converter();
        converter.convertToPostFix(input);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("123");
        expected.add("123.123");
        expected.add("+");

        assertEquals( expected, converter.getPostFix() );
    }

    @Test
    void testConverterCaseTwo() {
        String input = "123+123.123" ;
        Converter converter = new Converter();
        converter.convertToPostFix(input);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("123");
        expected.add("123.123");
        expected.add("+");
        assertEquals( expected, converter.getPostFix() );
    }

    @Test
    void testConverterCaseThree() {
        String input = "10/100*10" ;
        Converter converter = new Converter();
        converter.convertToPostFix(input);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("10");
        expected.add("100");
        expected.add("/");
        expected.add("10");
        expected.add("*");
        assertEquals( expected, converter.getPostFix() );
    }

}