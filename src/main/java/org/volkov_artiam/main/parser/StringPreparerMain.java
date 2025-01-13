package org.volkov_artiam.main.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */
public class StringPreparerMain {
    public static void main(String args[]) {

    	StringPreparer preparer = new StringPreparer(); 	

    	System.out.println();
    	System.out.println(preparer.convert("+-2") );

    	/**/
		String[] strings = new  String[]{ "123.+123.123+12.0", "10.0+20","1.0+20.",	"1.0+20.00", "-21+3.+", "3.", "3.01", "-3", "3+", "3+2", } ; 
    	for (String s : strings) {
			System.out.println(preparer.convert(s) );
		}
    	/**/
    	List<String> ls = new ArrayList<String>(Arrays.asList(strings));
    	List<String> converted = new ArrayList<String>();
    	for (String s : strings) {
    		converted.add(preparer.convert(s) );
		}
    	System.out.println(converted);
		/**/
    }
       
}
