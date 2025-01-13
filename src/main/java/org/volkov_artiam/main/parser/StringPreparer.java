package org.volkov_artiam.main.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringPreparer{
	
	private String numberPattern = "\\d+";
	private String dotAndZero = ".0";
	private String zero = "0";
	private String dot = ".";
	List <String> subStrings = new ArrayList<>();
	
	public String convert(String input) {
		subStrings.clear();
		String output = input;
		while(!output.equals("") ) {

			//только число добавляем точку с запятой
			if(isNumeric(output)) {
				subStrings.add(output + dotAndZero);
				output = "";
			} 

			//в выражениие найдено число? Да
			if(isFindedNumberIn(output) ) {
				Parts parts = new Parts(output);

				//Следующим за последоватлеьностью цифрой может быть: 	
				//-конец строки тогда добавляем точку с запятой
				if(parts.afterIsEmpty() ) {
					subStrings.add(parts.before);
					subStrings.add(parts.finded);
					subStrings.add(dotAndZero);
					output = "";
					break;
				}
				
				//-точка  
				if(parts.afterIsDot() ) {
					// если нет продолжения добавляем ноль и закончили
					//4.
					if(parts.nextToAfterIsEmpty() ) {
						subStrings.add(parts.before);
						subStrings.add(parts.finded);
						subStrings.add(parts.after);
						subStrings.add(zero);
						output = "";
						break;
					}
					// есть продолжение и в продолжении нет цифр
					//4.x
					Parts nextParts = new Parts(parts.nextToAfter);
					if(!parts.nextToAfterIsEmpty() &&  !nextParts.isFinded()  ) {
						subStrings.add(parts.before);
						subStrings.add(parts.finded);
						subStrings.add(parts.after);
						subStrings.add(zero);
						subStrings.add(parts.nextToAfter);
						output = "";
						break;
					} 
					// есть продолжение и в продолжении есть цифры
					if(!parts.nextToAfterIsEmpty() &&  nextParts.isFinded() ) {
						//78.45
						if(nextParts.before.isEmpty()) {
							subStrings.add(parts.before);
							subStrings.add(parts.finded);
							subStrings.add(parts.after);
							subStrings.add(nextParts.finded);
							output = nextParts.after + nextParts.nextToAfter;
						}
						//78.xx78
						if(!nextParts.before.isEmpty()) {
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
					subStrings.add(parts.before);
					subStrings.add(parts.finded);
					subStrings.add(dotAndZero);
					subStrings.add(parts.after);
					output = parts.nextToAfter;
				}
			}

			//в выражениие найдено число? Нет (Закончили)
			if(!isFindedNumberIn(output) ) { 
				subStrings.add(output);
				output = "";
				break;
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
			return after.equals(dot);
		}
		boolean isAfterOperator() {
			boolean isNotNumber = !isNumeric(this.after); 
			boolean afterIsNotDot = !this.afterIsDot(); 
			return (isNotNumber && afterIsNotDot);
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
	
	private boolean isFindedNumberIn(String str) {
		Matcher matcher = Pattern.compile(numberPattern).matcher(str);
		return matcher.find();
	}

	private boolean isCustomNumeric(String str) {
		try {
			Double.parseDouble(str);	// 10. преобразует в 10.0					
			return true;
		} catch(NumberFormatException e){
			return false;
	    }
	}

	private boolean isNumeric(String str) {
		int counter = 0;
        Pattern pattern = Pattern.compile(numberPattern);
        Matcher matcher = pattern.matcher(str);
		while(matcher.find()) {
			counter++;
		}
		boolean isMatch = counter == 1;
		boolean isCustomNumeric = isCustomNumeric(str);
		boolean isContainsDot = str.contains(".");
		boolean isNumeric = isMatch && isCustomNumeric && !isContainsDot;
		return isNumeric;
	}
	
}
