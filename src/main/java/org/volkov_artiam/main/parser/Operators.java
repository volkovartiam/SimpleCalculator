package org.volkov_artiam.main.parser;

import org.volkov_artiam.operators.Operator;

interface Operators {

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

    boolean isMathOps(String exp);
    boolean isOperator(String exp);

}
