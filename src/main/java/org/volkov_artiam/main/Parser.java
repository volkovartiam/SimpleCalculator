package org.volkov_artiam.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.volkov_artiam.operators.Operators;

public class Parser {

    Operators operators = new Operators();
    ArrayList<String> operatorsPatterns = operators.getOperatorsPatternsList();

    ArrayList<String> eqList = new ArrayList<>();
    ArrayList<String> unknownSymbolsList = new ArrayList<>();;

    public void parse(String exp) {

        if(isNotNullAndNotEmpty(exp)) {
        }
        exp = removeSpaces(exp);
        String part = "";

        while(!exp.equals("")) {

            HashMap<String, Integer> symbols = new LinkedHashMap<>();

            for(String opsPattern : operatorsPatterns) {
                Pattern pattern = Pattern.compile(opsPattern);
                Matcher matcher = pattern.matcher(exp);

                if(matcher.find()) {
                    int start = matcher.start();
                    // В hashmap заносятся оператор и индекс символа начала строки
                    symbols.put(opsPattern, start);
                } else {
                    // если не найден заностится -1
                    symbols.put(opsPattern, -1);
                }
            }

            // Сортируем с фильтром для найденных значений
            Map<String, Integer> sortedOperators = symbols.entrySet().stream()
                    .filter(x -> x.getValue() >= 0)
                    .sorted(Map.Entry.comparingByValue())
                    .collect(
                            Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                    LinkedHashMap::new));

            // Проверяем есть ли операторы в наличии
            Iterator<String> itr = sortedOperators.keySet().iterator();
            if(itr.hasNext()) {
                String key = itr.next();
                Pattern pattern = Pattern.compile(key);
                Matcher matcher = pattern.matcher(exp);

                // Дополнительная проверка
                if(matcher.find()) {
                    int start = matcher.start();
                    int end = matcher.end();

                    // Символ должен быть найден с индекса 0
                    if(start == 0) {
                        part = exp.substring(start, end);
                        eqList.add(part);
                        exp = exp.substring(end);
                        //System.out.println(exp);
                    } else {
                        part = exp.substring(0, start);
                        unknownSymbolsList.add(part);
                        exp = exp.substring(sortedOperators.get(key) );
                        //System.out.println(exp);
                    }
                }
            } else {
                part = exp;
                unknownSymbolsList.add(part);
                exp = "";
            }

			/*
			System.out.println("Выражение =" + eqList);
			System.out.println("Не допустимые символы =" + unknownList);
			*/
        }

        //System.out.println("Окончательное выражение имеет вид =" + eqList);
        //System.out.println("Не допустимые символы =" + unknownList);

    }


    // Проверка на наличие пустой строки или null
    public boolean isNotNullAndNotEmpty(String input) throws IllegalArgumentException, NullPointerException{
        boolean isNotNullAndNotEmpty = true;
        if (input.equals(null)) {
            isNotNullAndNotEmpty = false;
            throw new NullPointerException("Строка не создана");
        } else if (input.isEmpty()) {
            isNotNullAndNotEmpty = false;
            throw new IllegalArgumentException("Пустая строка");
        }
        return isNotNullAndNotEmpty;
    }


    private String removeSpaces(String str) {
        str = str.replaceAll("\\s+","") ;
        str = str.replaceAll("\\t+","") ;
        str = str.replaceAll("\\n+","") ;
        return str;
    }

    public ArrayList<String> getEquationList(){
        return eqList;
    }

    public ArrayList<String> getUknownSymbolsList(){
        return unknownSymbolsList;
    }

}