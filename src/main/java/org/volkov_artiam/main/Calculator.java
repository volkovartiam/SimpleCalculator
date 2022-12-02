package org.volkov_artiam.main;

import java.util.ArrayList;
import java.util.Stack;
import org.volkov_artiam.operators.Operators;

public class Calculator {

    Operators ops = new Operators();
    ArrayList<String> calculatorSteps = new ArrayList<>();
    String answer = "";

    public void calculate(String exp) {

        Converter converter = new Converter();
        converter.validConvertToPostFix(exp);
        ArrayList<String> postfixExpr = converter.getPostFix();
        //System.out.println("Выражение в постфиксном виде " + postfixExpr );

        Stack<Double> stackDouble = new Stack<Double>();
        int counter = 0;

        for(String eqPart : postfixExpr) {
            if(ops.isNumber(eqPart)) {
                stackDouble.push( (Double) Double.valueOf(eqPart)  );
            }

            else if (ops.isOperator(eqPart) ) {
                if(!stackDouble.isEmpty() ) {
                    double first = stackDouble.pop();

                    if(ops.isUnary(eqPart) ) {
                        double result = Execute(eqPart, first);

                        String step =  counter + ")" + first +  eqPart + "=" + result;
                        calculatorSteps.add(step);

                        stackDouble.push(result);
                        counter++;

                    } else {
                        double second  = stackDouble.pop();
                        double result = Execute(eqPart, first, second);

                        String step =  counter + ")" + second +  eqPart + first + "=" + result;
                        calculatorSteps.add(step);

                        stackDouble.push(result);
                        counter++;
                    }
                }
            }
        }

        if( !stackDouble.isEmpty() ) {
            answer = stackDouble.pop().toString();
        }
		/*
		for(String step : calculatorSteps) {
			System.out.println(step);
		}
		*/
    }


    double Execute(String operation, double first, double second)  {
        if( ops.isSum(operation) ) {
            return first + second;
        }

        if( ops.isSub(operation) ) {
            return second - first;
        }

        if( ops.isMult(operation) ) {
            return first * second;
        }

        if( ops.isDiv(operation) ) {
            if(first == 0) {
                throw new IllegalArgumentException("Деление на ноль не возможно");
            }
            return  second/ first;
        } else {
            throw new IllegalArgumentException("Неизвестное выражение с бинарным оператором");
        }
    }


    double Execute(String operation, double first) throws IllegalArgumentException {
        if( ops.isSin(operation) ) {
            return Math.sin(first);
        } else {
            throw new IllegalArgumentException("Неизвестное выражение с унарным оператором");
        }
    }

    public String getAnswer() {
        return answer;
    }

    public ArrayList<String> getSteps() {
        return calculatorSteps;
    }

}
