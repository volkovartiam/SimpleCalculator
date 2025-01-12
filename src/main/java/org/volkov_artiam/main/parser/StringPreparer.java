package org.volkov_artiam.main.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringPreparer {
	
	private String numberPattern = "\\d+";
//	private String numberPattern = "^\\d+$";
	
	private String dotAndZero = ".0";
	private String zero = "0";
	private String dot = ".";
	List <String> subStrings = new ArrayList<>();

	
	public String convert(String input) {
		subStrings.clear();
		System.out.println("-0");
		String output = input;
		//System.out.println(isNumeric(""));
		while(!output.equals("") ) {

			//только число добавляем точку с запятой
			if(isNumeric(output)) {
				System.out.println("-1");
				subStrings.add(output + dotAndZero);
				output = "";
			} 
			//в выражениие найдено число? Да
			if(numberPatternIsFinded(output) ) {
				System.out.println("-2");
				Parts parts = new Parts(output);
				//System.out.println(parts);
				//Следующим за последоватлеьностью цифрой может быть: 	
				//-конец строки тогда добавляем точку с запятой
				if(parts.afterIsEmpty() ) {
					subStrings.add(parts.before);
					subStrings.add(parts.finded);
					subStrings.add(dotAndZero);
					output = "";
				}
				
				//-точка  
				if(parts.afterIsDot() ) {
					System.out.println(parts);
					// если нет продолжения добавляем ноль и закончили
					//4.
					if(parts.nextToAfterIsEmpty() ) {
						System.out.println("-4");
						subStrings.add(parts.before);
						subStrings.add(parts.finded);
						subStrings.add(parts.after);
						subStrings.add(zero);
						output = "";
					}
					
					// есть продолжение и в продолжении нет цифр
					//4.x
					Parts nextParts = new Parts(parts.nextToAfter);
					if(!parts.nextToAfterIsEmpty() &&  !nextParts.isFinded()  ) {
						System.out.println("-6");
						subStrings.add(parts.before);
						subStrings.add(parts.finded);
						subStrings.add(parts.after);
						subStrings.add(zero);
						subStrings.add(parts.nextToAfter);
						output = "";
					} 
					
					// есть продолжение и в продолжении есть цифры
					if(!parts.nextToAfterIsEmpty() &&  nextParts.isFinded() ) {
						System.out.println("-7");
						//78.45
						if(nextParts.before.isEmpty()) {
							System.out.println("-8");
							subStrings.add(parts.before);
							subStrings.add(parts.finded);
							subStrings.add(parts.after);
							subStrings.add(nextParts.finded);
							output = nextParts.after + nextParts.nextToAfter;
						}
						//78.xx78
						if(!nextParts.before.isEmpty()) {
							System.out.println("-9");
							subStrings.add(parts.before);
							subStrings.add(parts.finded);
							subStrings.add(parts.after);
							subStrings.add(zero);
							subStrings.add(nextParts.before);
							output = nextParts.finded + nextParts.after + nextParts.nextToAfter;
						} 
					}
				}
				
				//знак тогда добавляем точку с запятой
				// 4+
				if( parts.isAfterOperator() ) {
					System.out.println("-10");
					subStrings.add(parts.before);
					subStrings.add(parts.finded);
					subStrings.add(dotAndZero);
					subStrings.add(parts.after);
					output = parts.nextToAfter;
				}
			}
			
			//в выражениие найдено число? Нет (Закончили)
			if(!numberPatternIsFinded(output) ) { 
				subStrings.add(output);
				output = "";
			}  
			
		} // end of while
				
    return stringFromList(subStrings);
	}

	
	private String stringFromList(List<String> list) {
		String out = "";
		for (String string : list) {
			out +=string; 
		}
		return out;
	}
	
	class Parts {
		String before = "";
		String finded = "";
		String after = "";
		String nextToAfter = "";
		String input = "";
		
		private boolean isFinded;
		
		public Parts(String str){
			input = str;
			Matcher matcher = Pattern.compile(numberPattern).matcher(str);
			isFinded = matcher.find();
			if(isFinded ) {
		        int start = matcher.start();
		        int end = matcher.end();
	        	before = str.substring(0, start);
		        finded = str.substring(start, end);
		        if(end < str.length() ) {
		        	after = str.substring(end, end+1);
		        } else {
		        	after = "";
		        }
		        
		        if( (str.length() - end) >= 2 ) {
		        	nextToAfter = str.substring(end + 1, str.length() );
		        } else {
		        	nextToAfter = "";
		        }
		        
		        
			}
		}
		
		boolean isFinded() {
			return isFinded;
		}
		
		boolean afterIsEmpty() {
			return after.equals("");
		}
		
		boolean nextToAfterIsEmpty() {
			return nextToAfter.isEmpty();
		}
		
		boolean afterIsDot() {
			return after.equals(".");
		}
		

		boolean isAfterOperator() {
			
			boolean isNotNumber = !Pattern.compile(numberPattern).matcher(this.after).find();
			boolean afterIsNotDot = !this.afterIsDot(); 
			return (isNotNumber && afterIsNotDot && !isNumeric(after));
		}
		
		@Override
		public String toString() {
			return "before=" + before + "\n" + 
				   "finded=" + finded + "\n" + 									
				   "after=" + after + "\n" + 
				   "nextToAfter=" + nextToAfter + "\n" +
				   "input=" + input + "\n";
		}
					
	}
	
	
	private boolean numberPatternIsFinded(String str) {
		Matcher matcher = Pattern.compile(numberPattern).matcher(str);
		return matcher.find();
	}

	
	
	private boolean isNotNumerisAndIsNotDot(String str) {
		return !isNumeric(str) && !isDot(str);
	}

	
	private boolean isDot(String str) {
		return str.equals(dot);
	}

	
	// 10. преобразует в 10.0
	private boolean isCustomNumeric(String str) {
		try {
			double x = Double.parseDouble(str);
			//System.out.println("x=" + x);
			return true;
		} catch(NumberFormatException e){
			return false;
	    }
	}
	
	
	// 10. преобразует в 10.0
	private boolean isNumeric(String str) {
		//boolean isMatch = Pattern.compile("\\d+").matcher(str).find();
		//boolean isMatch = Pattern.compile(numberPattern).matcher(str).find();
		int counter = 0;
        Pattern pattern = Pattern.compile(numberPattern);
        Matcher matcher = pattern.matcher(str);
		while(matcher.find()) {
			//System.out.println("counter=" + counter);
			counter++;
		}
		boolean isMatch = counter == 1;
		//System.out.println(isMatch);
		boolean isCustomNumeric = isCustomNumeric(str);
		//System.out.println(isCustomNumeric);
		boolean isContainsDot = str.contains(".");
		
		return (isMatch && isCustomNumeric && !isContainsDot);
	}
	

}
