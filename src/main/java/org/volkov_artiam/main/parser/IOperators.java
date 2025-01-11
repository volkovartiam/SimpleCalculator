package org.volkov_artiam.main.parser;

public interface IOperators {

    boolean isNumber(String exp);
    
    boolean isBrackOpen(String exp);
    boolean isBrackClosed(String exp);
    boolean isBrack(String exp);
    
    boolean isSum(String exp);
    boolean isSub(String exp);
    boolean isMult(String exp);
    boolean isDiv(String exp);
    
    boolean isSin(String exp);
	
    boolean isUnary(String exp);
    boolean isBinary(String exp);

    boolean isMathOperator(String exp);
    boolean isOperator(String exp);

}
