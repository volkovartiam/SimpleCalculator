package org.volkov_artiam.main.parser;

import java.util.List;

import org.volkov_artiam.operators.Operators;

class ValidatedParser extends Parser {

    /* переписать для исключения связи с операторами
     * 
     */
    Operators ops = new Operators();
    Parser parser;
    SpacesRemover spacesRemover;

    public ValidatedParser(){
    	parser = new Parser();
    	spacesRemover = new SpacesRemover();
    }
    
    @Override
    void setOperatorsPatternsList(List<String> operatorsPatterns) {
    	super.setOperatorsPatternsList(operatorsPatterns);
    }
    
    
    // Проверка правильности записи операторов
    public boolean isValidString(String input) throws IllegalArgumentException {

    	input = spacesRemover.removeSpaces(input);
        parser.parse(input);
        List<String> eqList = parser.getEqList();
        List<String> unknownsList = parser.getUknownsList();
        
        checkUnknowns(unknownsList);
        checkBrackets(eqList);

        for(int i = 0; i < eqList.size(); i++ ) {
        	String current = "";
        	String previous = "";
        	String next = "";
        	String before_previous = "";
        	
        	current = eqList.get(i);
            if(i + 1 < eqList.size() ) 	{ 
            	next = eqList.get(i + 1); 
            }
            if(i - 1 >= 0) { 
            	previous = eqList.get(i -1); 
            }
            if(i - 2 >= 0) { 
            	before_previous = eqList.get(i - 2); 
            }

            checkEmptyBrackets(ops.isBrackOpen(current), ops.isBrackClosed(next) );
            checkTwoOperatorsNear(ops.isBinary(current), ops.isBinary(next) );
            checkSymbolBetweenBracketsAndNumbers(ops.isNumber(current), ops.isBrackOpen(next) );

            

            //отсутствие символа между скобкой и числом )4
            if(ops.isBrackClosed(current) & ops.isNumber(next) ) {
                throw new IllegalArgumentException("Mistake №6 отсутствие символа между скобкой и числом"  );
            }

            // число или оператор в скобке
            if( !ops.isUnary(before_previous) &  ops.isBrackOpen(previous) & ( ops.isMathOps(current) )  & ops.isBrackClosed(next) ) {
                throw new IllegalArgumentException("Mistake №7 число или оператор в скобке"  );
            }

            // бинарный оператор заканчивается скобкой
            if( ops.isBinary(current)  &  ops.isBrackClosed(next)  ) {
                throw new IllegalArgumentException("Mistake №8.1 бинарный оператор заканчивается скобкой"  );
            }

            // бинарный оператор заканчивается в конце выражения
            if( ops.isBinary(current)  & (i == eqList.size() - 1)    ) {
                throw new IllegalArgumentException("Mistake №8.2 бинарный оператор заканчивается без цифры"  );
            }


            // синус не со скобкой
            if( ops.isSin(current)  &  !ops.isBrackOpen(next) ) {
                throw new IllegalArgumentException("Mistake №9 синус не со скобкой"  );
            }

            // число и сразу унарный оператор
            if( ops.isNumber(current)  &  ops.isUnary(next) ) {
                throw new IllegalArgumentException("Mistake №10 число и сразу унарный оператор"  );
            }

            // оператор рядом с унарным оператором в начале строки
            if( ops.isBinary(current) & ops.isUnary(next) & (i == 0)   ) {
                throw new IllegalArgumentException("Mistake №11 оператор рядом с унарным оператором в начале строки"  );
            }

            // оператор рядом с унарным оператором после открывающейся скобки
            if( ops.isBinary(current) & ops.isUnary(next) & ops.isBrackOpen(previous)  ) {
                throw new IllegalArgumentException("Mistake №12 оператор рядом с унарным оператором после скобки"  );
            }
        }
        return true;
    }

    
    //отсутствие символа между числом и скобкой 4(
    void checkSymbolBetweenBracketsAndNumbers(boolean current, boolean next) {
        if(current & next) {
            throw new IllegalArgumentException("Mistake №5 отсутствие символа между числом и скобкой"  );
        }
    }
    
    
    // два бинарных оператора рядом
    void checkTwoOperatorsNear(boolean current, boolean next) {
    	if(current & next )  {
    		throw new IllegalArgumentException("Mistake №4 два бинарных оператора рядом"  );
    	}
    }
        
	// выражение состоит из пустых скобок
	void checkEmptyBrackets(boolean current, boolean next) {
		if(current & next) {
		    throw new IllegalArgumentException("Mistake №3 выражение состоит из пустых скобок"  );
		}
	}
	
    // проверка количества открытых и закрытых скобок
	void checkBrackets(List<String> eqList) {
        int countOpen = 0;
        int countClosed = 0;

        for(String eqPart : eqList) {
            if(ops.isBrackOpen(eqPart)) {
                countOpen++;
            } else if(ops.isBrackClosed(eqPart)  ) {
                countClosed++;
            }
        }
        if(countOpen!=countClosed ) {
            throw new IllegalArgumentException("Mistake №2 Ошибка в написании скобок");
        } 
	}
	
    // Проверка наличия неизвестных символов
	void checkUnknowns(List<String> unknownsList) {
        if(unknownsList.size() > 0) {
            throw new IllegalArgumentException("Mistake №1 имееются неизвестные символы");
        }
	}
    

}
