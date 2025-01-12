package org.volkov_artiam.main.parser;

import java.util.ArrayList;
import java.util.List;

public class SpacesRemover {

    private final int MAX_STRING_SIZE = 100;
	private ArrayList<String> spacesPatterns = new ArrayList<>();
	
    private void addSpacesPattern(String spacesPattern) {
    	spacesPatterns.add(spacesPattern);
    }
    
    private void createSpacesPatternsList() {
    	addSpacesPattern("\\s+");
    	addSpacesPattern("\\t+");
    	addSpacesPattern("\\n+");
    }
        
    public List<String> getSpacesPatternList() {
    	return spacesPatterns;
    }
        
    public SpacesRemover() {
    	createSpacesPatternsList();
    }
        
    /* Удаление пробелов из строки */
	public String removeSpaces(String input) throws NullPointerException, IllegalArgumentException {
        if (input == null) {
        	throw new NullPointerException("Input is null");
        }
        if (input.equals("")) {
            throw new IllegalArgumentException("Строка пуста");
        }
        if (input.length() > MAX_STRING_SIZE) {
            throw new IllegalArgumentException("Превышено максимальное число символов");
        }
		String output = input;
		for (String pattern : spacesPatterns) {
			output = output.replaceAll(pattern,"") ;
		}
		return output;
	}
      

}