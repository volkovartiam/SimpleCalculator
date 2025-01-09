package org.volkov_artiam.main.parser;

import java.util.List;

import org.volkov_artiam.operators.OperatorsFeatures;

public class ValidatedParser extends Parser implements Operators{

    public OperatorsFeatures operators = new OperatorsFeatures();
    Parser parser = new Parser();
    SpacesRemover spacesRemover = new SpacesRemover();

    public ValidatedParser(){
    	parser.setOperatorsPatternsList(operators.getPatternsList() );
    }
       
    public List<String> parse(String input){
    	List<String> eqList = null;
    	if(isValidString(input) ) {
    		eqList = parser.parse(input);
    	}
    	return eqList;
    }
    
    // Проверка правильности записи операторов
    public boolean isValidString(String input) throws IllegalArgumentException {
    	input = spacesRemover.removeSpaces(input);
        List<String> eqList = parser.parse(input);
        List<String> unknownsList = parser.getUknownsList();
        
        if(unknownsList.size() > 0) {
            throw new IllegalArgumentException("Mistake №1 имееются неизвестные символы");
        }
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

    		if(isBrackOpen(current) & isBrackClosed(next) ) {
    		    throw new IllegalArgumentException("Mistake №3 выражение состоит из пустых скобок"  );
    		}
            
	    	if(isBinary(current) & isBinary(next) )  {
	    		throw new IllegalArgumentException("Mistake №4 два бинарных оператора рядом"  );
	    	}
    		
            if(isNumber(current) & isBrackOpen(next)) {
                throw new IllegalArgumentException("Mistake №5 отсутствие символа между числом и скобкой"  );
	        }
	    	
            if(isBrackClosed(current) & isNumber(next) ) {
                throw new IllegalArgumentException("Mistake №6 отсутствие символа между скобкой и числом"  );
            }

            if( !isUnary(before_previous) &  isBrackOpen(previous) & ( isMathOps(current) )  & isBrackClosed(next) ) {
                throw new IllegalArgumentException("Mistake №7 число или оператор в скобке"  );
            }

            if( isBinary(current)  &  isBrackClosed(next)  ) {
                throw new IllegalArgumentException("Mistake №8.1 бинарный оператор заканчивается скобкой"  );
            }

       // Уточнить на этапе тестирования     
            if( isBinary(current)  & (i == eqList.size() - 1)    ) {
                throw new IllegalArgumentException("Mistake №8.2 бинарный оператор заканчивается без цифры"  );
            }

            if( isSin(current)  &  !isBrackOpen(next) ) {
                throw new IllegalArgumentException("Mistake №9 синус не со скобкой"  );
            }

            if( isNumber(current)  &  isUnary(next) ) {
                throw new IllegalArgumentException("Mistake №10 число и сразу унарный оператор"  );
            }

        // Уточнить на этапе тестирования  
            if( isBinary(current) & isUnary(next) & (i == 0)   ) {
                throw new IllegalArgumentException("Mistake №11 оператор рядом с унарным оператором в начале строки"  );
            }

            if( isBinary(current) & isUnary(next) & isBrackOpen(previous)  ) {
                throw new IllegalArgumentException("Mistake №12 оператор рядом с унарным оператором после скобки"  );
            }
        }
        return true;
    }

    
    // проверка количества открытых и закрытых скобок
	void checkBrackets(List<String> eqList) {
        int countOpen = 0;
        int countClosed = 0;

        for(String eqPart : eqList) {
            if(operators.isBrackOpen(eqPart)) {
                countOpen++;
            } else if(operators.isBrackClosed(eqPart)  ) {
                countClosed++;
            }
        }
        if(countOpen!=countClosed ) {
            throw new IllegalArgumentException("Mistake №2 Ошибка в написании скобок");
        } 
	}
	

	@Override
	public boolean isNumber(String exp) {
		return operators.isNumber(exp);
	}

	@Override
	public boolean isBrackOpen(String exp) {
		return operators.isBrackOpen(exp);
	}

	@Override
	public boolean isBrackClosed(String exp) {
		return operators.isBrackClosed(exp);
	}

	@Override
	public boolean isBrack(String exp) {
		return operators.isBrack(exp);
	}

	@Override
	public boolean isSum(String exp) {
		return operators.isSum(exp);
	}

	@Override
	public boolean isSub(String exp) {
		return operators.isSub(exp);
	}

	@Override
	public boolean isMult(String exp) {
		return operators.isMult(exp);
	}

	@Override
	public boolean isDiv(String exp) {
		return operators.isDiv(exp);
	}

	@Override
	public boolean isSin(String exp) {
		return operators.isSin(exp);
	}

	@Override
	public boolean isUnary(String exp) {
		return operators.isUnary(exp);
	}

	@Override
	public boolean isBinary(String exp) {
		return operators.isBinary(exp);
	}

	@Override
	public boolean isMathOps(String exp) {
		return operators.isMathOps(exp);
	}

	@Override
	public boolean isOperator(String exp) {
		return operators.isOperator(exp);
	}
    

}
