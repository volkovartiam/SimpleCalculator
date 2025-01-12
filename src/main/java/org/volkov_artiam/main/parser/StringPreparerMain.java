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
    	
    	//System.out.println(preparer.convert("123.+123.123+12.0") );
    	System.out.print("");
    	System.out.println(preparer.convert("311-2+3") );

    	//System.out.println(preparer.convert("1.0+20.+3+2") );
    	System.out.print("");

    	
    	//System.out.println(preparer.convert("123.+123.123") );
    	//System.out.println(preparer.convert("123.+") );
    	
    	//System.out.println(preparer.convert("10.023") );
    	/*	System.out.println(preparer.convert("+10") );
    	System.out.println(preparer.convert("10+") );

    	
    	System.out.println(preparer.convert("10.") );

    	//System.out.println(preparer.convert("21+3.0") );
//    	System.out.println(preparer.convert("21.00+10") );

    	/**/
		String[] strings = new  String[]{ "123.+123.123+12.0", //"21+3.+", "3.", "3.01", "3", "3+", "3+2", 
										 "1","10.0+20","1.0+20.",	"1.0+20.00",  //"21+3.0", "21+3.", "21+3", "21+3+", 
										//"+3.0+123.+"//"21+3.+","21+3.0", "21+3.0+", "21+3.0+1" 
																					};
    	for (String s : strings) {
			System.out.println(preparer.convert(s) );
		}
    	// System.out.print("123");
    	
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
