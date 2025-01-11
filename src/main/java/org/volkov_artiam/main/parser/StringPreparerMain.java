package org.volkov_artiam.main.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */
public class StringPreparerMain {
    public static void main(String args[]) {

    	StringPreparer preparer = new StringPreparer(); 	
//    	System.out.println(preparer.convert("+10") );
//    	System.out.println(preparer.convert("21+3.0") );
//    	System.out.println(preparer.convert("21.00+10") );

 
		String[] strings = new  String[]{ //"123.120+123.123", //"21+3.+", "3.", "3.01", "3", "3+", "3+2", 
										"1.0+20",	"1.0+20.0",  //"21+3.0", "21+3.", "21+3", "21+3+", 
										//"+3.0+123.+"//"21+3.+","21+3.0", "21+3.0+", "21+3.0+1" 
																					};
    	for (String s : strings) {
			System.out.println(preparer.convert(s) );
		}
    	// System.out.print("123");
    	/**/
    	

    }
       

    
}
