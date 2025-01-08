package org.volkov_artiam.main.parser;

public class SpaceRemoverMain {
    public static void main(String args[]) {

    	SpacesRemover spacesRemover = new SpacesRemover();
    	System.out.println(spacesRemover.getSpacesPatternList() + " - паттерны пропусков");
    	
    	String input = "1 2   12 13  31  1 21 ";
    	String stringWithoutSpaces = spacesRemover.removeSpaces(input);
    	System.out.println(stringWithoutSpaces + " - строка без пробелов");
    	
    	try {
        	stringWithoutSpaces = spacesRemover.removeSpaces(null);
		} catch (NullPointerException e) {
        	System.out.println(e.getMessage() + " - исключение указания на null");
		}
    	
    	try {
        	stringWithoutSpaces = spacesRemover.removeSpaces("");
		} catch (IllegalArgumentException e) {
        	System.out.println(e.getMessage() + " - исключение пустой строки");
		}
    	
    	try {
    		input = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
        	stringWithoutSpaces = spacesRemover.removeSpaces(input);
        	System.out.println(stringWithoutSpaces);
		} catch (IllegalArgumentException e) {
        	System.out.println(e.getMessage() + " - исключение для строки с максимальным значением символов");
		}
    }
}
