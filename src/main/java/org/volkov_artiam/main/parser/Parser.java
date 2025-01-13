package org.volkov_artiam.main.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {

    private final long MAX_TIME_FOR_PARSING = 5000; 
	
	private List<String> operatorsPatterns = new ArrayList<>();
    private List<String> eqList = new ArrayList<>();
    private List<String> unknownsList = new ArrayList<>();

    List<String> getOperatorsPatternsList(){ return operatorsPatterns; }
    List<String> getEqList(){ return eqList; }
    List<String> getUknownsList(){ return unknownsList; }

    void setOperatorsPatternsList(List<String> operatorsPatterns){
    	this.operatorsPatterns = operatorsPatterns;
    }
    
    private void setDefaultOperatorsPatterns() {
    	operatorsPatterns.add("\\+");
    	operatorsPatterns.add("\\*");
    	operatorsPatterns.add("\\d+\\.\\d+");
    }
    
    Parser() {
    	setDefaultOperatorsPatterns();
    }
    
    /*
     * Добавить исключение по времени
     * Добавить переопределение toString
     * */
    List<String> parse(String input) throws IllegalStateException, IllegalArgumentException{
    	if(input.isEmpty() ) {
    		throw new IllegalArgumentException();
    	} 
        eqList.clear();
        unknownsList.clear();
    	
    	long startTime = System.currentTimeMillis();
        while(!input.equals("")) {

            HashMap<String, Integer> findedPatterns = findPatterns(input);
            HashMap<String, Integer> sortedPatterns = sortPatterns(findedPatterns);


        	input = parsedByPatterns(input, sortedPatterns);
            checkMaxTime(startTime, System.currentTimeMillis());
        }
        return eqList; 
    }

    /*
     * метод разделяет строку на паттерны и неизвестные символы
     * */
	String parsedByPatterns(String input, HashMap<String, Integer> sortedPatterns) {
		Iterator<String> itr = sortedPatterns.keySet().iterator();
		if(itr.hasNext()) {
			String pattern = itr.next();
		    Matcher matcher = Pattern.compile(pattern).matcher(input);

		    if(matcher.find()) {
		        int start = matcher.start();
		        int end = matcher.end();
		        if(start == 0) {
		            String findedPattern = input.substring(start, end);
		            eqList.add(findedPattern);
		            input = input.substring(end);
		        } else {
		        	String unknownSymbol = input.substring(0, start);
		            unknownsList.add(unknownSymbol);
		            input = input.substring(sortedPatterns.get(pattern) );
		            //break;
		        }
		    }
		} 
		else{
		    unknownsList.add(input);
		    input = "";
		}
		return input;
	}

    
    /*
     * В hashmap остаются паттерны имеющиеся только в первоначальной строке, остальные исключаются
     * */
    HashMap<String, Integer> sortPatterns(HashMap<String, Integer> symbols) {
    	return symbols.entrySet().stream()
		        .filter(x -> x.getValue() >= 0)
		        .sorted(HashMap.Entry.comparingByValue())
		        .collect( Collectors.toMap(e -> e.getKey(), e -> e.getValue(), 
		        						  (e1, e2) -> e2, LinkedHashMap::new));
	}

    
    /*
     * В hashmap заносятся паттерн оператора (числа в том числе) и индекс начала строки этого паттерна 
     * если не найден заностится -1
     * */
	HashMap<String, Integer> findPatterns(String input) {
		HashMap<String, Integer> findedPatterns = new HashMap<>();

		for(String pattern : operatorsPatterns) {
		    Matcher matcher = Pattern.compile(pattern).matcher(input);
		    if(matcher.find()) {
		        int start_index = matcher.start();
		        findedPatterns.put(pattern, start_index);
		    } else {
		        findedPatterns.put(pattern, -1);
		    } 
		}
		return findedPatterns;
	}

    
    private void checkMaxTime(long startTime, long endTime) {
    	long time = endTime - startTime;
    	if(time > MAX_TIME_FOR_PARSING) {
    		throw new IllegalStateException("Превышено время парсинга");
    	}
    }

    
	@Override
	public String toString() {
		return 	"Parser [\n" + 
				"operatorsPatterns=" + operatorsPatterns + "\n" + 
				"eqList=" + eqList + "\n" +  
				"unknownSymbolsList="
				+ unknownsList + "\n]";
	}
        	

}