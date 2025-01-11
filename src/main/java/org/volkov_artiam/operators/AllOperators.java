package org.volkov_artiam.operators;

import java.util.ArrayList;

import org.volkov_artiam.main.parser.IOperators;

public class AllOperators implements IOperators {

    public ArrayList<Operator> operators = new ArrayList<>();

    Operator numberFractional = new NumberFractional();
    //Operator number = new Number();

    Operator bracketOpened = new BracketOpened();
    Operator bracketClosed = new BracketClosed();

    Operator sum = new Sum();
    Operator sub = new Sub();
    Operator mult = new Mult();
    Operator div = new Div();

    Operator sin = new Sin();

    public AllOperators(){
        //operators.add(number);
    	operators.add(numberFractional);

        operators.add(bracketOpened);
        operators.add(bracketClosed);

        operators.add(sum);
        operators.add(sub);
        operators.add(mult);
        operators.add(div);

        operators.add(sin);

    }

    public boolean isNumber(String exp) {
        return /*number.isFindedWithPatternIn(exp) ||*/  numberFractional.isFindedWithPatternIn(exp) ;
    }


    public boolean isBrackOpen(String exp) {
        return bracketOpened.isFindedWithPatternIn(exp);
    }

    public boolean isBrackClosed(String exp) {
        return bracketClosed.isFindedWithPatternIn(exp);
    }

    public boolean isBrack(String exp) {
        return (isBrackClosed(exp) ||  isBrackClosed(exp));
    }



    public boolean isSum(String exp) {
        return sum.isFindedWithPatternIn(exp);
    }

    public boolean isSub(String exp) {
        return sub.isFindedWithPatternIn(exp);
    }

    public boolean isMult(String exp) {
        return mult.isFindedWithPatternIn(exp);
    }

    public boolean isDiv(String exp) {
        return div.isFindedWithPatternIn(exp);
    }


    public boolean isSin(String exp) {
        return sin.isFindedWithPatternIn(exp);
    }


    public ArrayList<String> getPatternsList(){
        ArrayList<String> operatorPattrens = new ArrayList<>();
        for(Operator operator : operators ) {
            operatorPattrens.add(operator.getPattern());
        }
        return operatorPattrens;
    }


    public boolean isUnary(String exp) {
        boolean isUnary = isSin(exp);
        return isUnary;
    }


    public boolean isBinary(String exp) {
        boolean isBinaryMathOperator = isSum(exp) || isSub(exp)|| isMult(exp)|| isDiv(exp) ;
        return isBinaryMathOperator;
    }

    public boolean isMathOperator(String exp) {
        boolean isMathOps = isUnary(exp) || isBinary(exp)|| isNumber(exp);
        return isMathOps;
    }



    public boolean isOperator(String exp) {
        for(Operator operator : operators ) {
            boolean isOperator = operator.isFindedWithPatternIn(exp);
            if(isOperator)  {
                return isOperator;
            }
        }
        return false;
    }

    public int getPriority(String exp) {
        int priority = -1;
        for(Operator operator : operators ) {
            if(operator.isFindedWithPatternIn(exp)) {
                return operator.getPriority();
            }
        }
        return priority;
    }

}
