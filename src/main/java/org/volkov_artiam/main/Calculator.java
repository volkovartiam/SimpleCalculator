package org.volkov_artiam.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.volkov_artiam.main.parser.PostfixConverter;

public class Calculator {

	PostfixConverter converter; 	
    List<String> calculatorSteps;
    String answer = "";
    int counter = 0;
    
    public Calculator() {
    	converter = new PostfixConverter();
    	calculatorSteps = new ArrayList<>();
	}

    public void calculate(String exp) {
        List<String> postfix = converter.convertToPostfix(exp);
        
        Stack<Double> stackDouble = new Stack<Double>();
        for(String eqPart : postfix) {
            if(converter.isNumber(eqPart)) {
                stackDouble.push(Double.parseDouble(eqPart) );
            }
            else if (converter.isInOperatorList(eqPart) ) {
                if(!stackDouble.isEmpty() ) {
                    double first = stackDouble.pop();

                    if(converter.isUnary(eqPart) ) {
                        double result = Execute(eqPart, first);

                        addStep( eqPart, "(", String.valueOf(first), ")", "=", String.valueOf(result) );

                        stackDouble.push(result);
                        counter++;
                    } else {
                        double second  = stackDouble.pop();
                        double result = Execute(eqPart, first, second);

                        addStep(String.valueOf(second), eqPart, String.valueOf(first), "=", String.valueOf(result) );
                        
                        stackDouble.push(result);
                        counter++;
                    }
                }
            }
        }
        if( !stackDouble.isEmpty() ) {
            answer = stackDouble.pop().toString();
        }
    }


	private void addStep(String ... strings) {
		String output = counter + ")";
		for (String string : strings) {
			output += string;
		}
		output += "\n";
		calculatorSteps.add(output);
	}

    
    double Execute(String operation, double first, double second)  {
        if( converter.isSum(operation) ) {
            return first + second;
        }

        if( converter.isSub(operation) ) {
            return second - first;
        }

        if( converter.isMult(operation) ) {
            return first * second;
        }

        if( converter.isDiv(operation) ) {
            if(first == 0) {
                throw new IllegalArgumentException("Деление на ноль не возможно");
            }
            return  second/ first;
        } else {
            throw new IllegalArgumentException("Неизвестное выражение с бинарным оператором");
        }
    }


    double Execute(String operation, double first) throws IllegalArgumentException {
    	double answer = 0;
    	if( converter.isSin(operation) ) {
    		answer = Math.sin(first * Math.PI/180 );
            return checkIfSmall(answer);
        } 
        if( converter.isCos(operation) ) {
    		answer = Math.cos(first * Math.PI/180 );
            return checkIfSmall(answer);
        } 
        else {
            throw new IllegalArgumentException("Неизвестное выражение с унарным оператором");
        }
    }

    private double checkIfSmall(double number) {
		if(Math.abs(number) < 0.000000000000001) {
			number =  0;
		}
		return number;
    }
    
    public String getAnswer() {
        return answer;
    }

    public String getSteps() {
    	String output = "";
    	for (String string : calculatorSteps) {
    		output += string;
		}
        return output;
    }

}
