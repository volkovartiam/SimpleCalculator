package org.volkov_artiam.main.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 */
public class ValidatedParserMain {
    public static void main(String args[]) {

    	ValidatedParser validatedParser = new ValidatedParser();
        String input = "123.0+123.0";
        
        
    	System.out.println(validatedParser.isValidString(input));
            	

    }
}
