package org.volkov_artiam.main.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringPreparer2 {
	
	private String numberPattern = "\\d+";
	private String dotAndZero = ".0";
	private String zero = "0";
	private String dot = ".";
	List <String> subStrings = new ArrayList<>();

	
	public String convert(String input) {
		subStrings.clear();
		String output = input;
		
		while(!output.equals("") ) {

			if(isNumeric(output)) {
				subStrings.add(output + dotAndZero);
				output = "";
			} 

	
				
				Matcher matcher = Pattern.compile(numberPattern).matcher(output);
				boolean isFinded = matcher.find();
			    if(isFinded) {
			        int start = matcher.start();
			        int end = matcher.end();
			        String finded = output.substring(start, end);
			        
					if(end == output.length() ){
						subStrings.add(output + dotAndZero);
						output = "";
			        } 
					
		        	String before = output.substring(0, end);
					String next = output.substring(end, end+1);
				
				
			}
				
			
			/*
			Matcher matcher = Pattern.compile(numberPattern).matcher(output);
			boolean isFinded = matcher.find();
		    if(isFinded) {
		        int start = matcher.start();
		        int end = matcher.end();
		        String finded = output.substring(start, end);
		        
				if(end == output.length() ){
					subStrings.add(output + dotAndZero);
					output = "";
		        } 
				
	        	String before = output.substring(0, end);
				String next = output.substring(end, end+1);
	        	
	        	if(isNumeric(finded) && isNotNumerisAndIsNotDot(next) ) {
	        		subStrings.add(before + dotAndZero + next);
	        		output = output.substring(end+1, output.length() );
	        	}
	        	
	        	if((output.length() - end) == 1 ) {
	        		System.out.println("yy");
	        		String after = output.substring(end+1, output.length() );
		        	if(isNumeric(finded) && isDot(next) ) {
	        			subStrings.add(before + dotAndZero + after);
	        			output = "";
		        	}
	        	}
	        	
	        	if( (output.length() - end) >= 2 ) {
	        		System.out.println("xx");
	        		String nextNext = output.substring(end+1, end+2);
		        	if(isNumeric(finded) && isDot(next) ) {
		        		if(isNumeric(nextNext) ) {
			        		subStrings.add(before + next + nextNext);
			        		output = output.substring(end+2, output.length() );
			        		if(isNumeric(output)) {
			        			subStrings.add(output);
			        			output = "";
			        		} 
		        		}else {
		        			subStrings.add(before + next + zero + nextNext + output.substring(0, end+2));
			        		output = output.substring(end+2, output.length() );
		        		}	
	        		} 

	        	}else {
		    	subStrings.add(output);
		    	output = "";
		    }
		/**/
		}
				
		output = "";
		for (String string : subStrings) {
			output +=string; 
		}
    return output;
	}

	
	private boolean isNotNumerisAndIsNotDot(String str) {
		return !isNumeric(str) && !isDot(str);
	}
	
	
	private boolean isDot(String str) {
		return str.equals(dot);
	}

	
	private boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch(NumberFormatException e){
			return false;
	    }
	}
	

}
