package org.volkov_artiam.main.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 */
public class ParserMain {
    public static void main(String args[]) {

    	Parser parser = new Parser(); 	
    	System.out.println(parser.getOperatorsPatternsList() + " - лист операторов" );
    	System.out.println(parser.getEqList() + " - лист с результатами (после парсинга) " );;
    	System.out.println(parser.getUknownsList() + " - лист с неизвестными символами (после парсинга) " );;
    	
    	
    	System.out.println();
    	//String input = "123.00+123.00";
        String input = "123.0++123.0";
        parser.parse(input);
    	System.out.println(parser);
            	
    	try {
        	input = "123.0-1231+1213/12*123" ;
        	Parser pars = new Parser();
        	pars.setOperatorsPatternsList(new ArrayList<>() );
        	pars.parse(input);
        	System.out.println(pars + " выражение с минусом (требуется отловить исключение по превышению времени)");
		} catch (Exception e) {
			System.out.println(e.getMessage() );
		}
    	    	
    	input = "123.00+123.0";
    	new Parser().parse(input);
    	
    	HashMap<String, Integer> finded = parser.findPatterns(input);
    	System.out.println(finded + " finded" );
    	
    	HashMap<String, Integer> sorted = parser.sortPatterns(finded);
    	System.out.println(sorted + " sorted" );
    	System.out.println(parser );
    	
    	
    	System.out.println();
    	List<String> operators = new ArrayList<>(List.of("\\+", "\\-", "\\*", "\\/", "\\d+\\.\\d+"));
    	parser.setOperatorsPatternsList(operators);
    	input = "123.00+123.00/89.0";
    	parser.parse(input);
    	System.out.println(parser);
    	
    }
}
