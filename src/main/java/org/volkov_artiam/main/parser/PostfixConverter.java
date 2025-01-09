package org.volkov_artiam.main.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class PostfixConverter implements Operators{

	ValidatedParser parser = new ValidatedParser();

    public List<String> convertToPostfix(String input){
    	List<String> postfixList = new ArrayList<>();
    	
    	List<String> eqList = parser.parse(input);
        Stack<String> stack = new Stack<String>();
    	
        for(String eqPart : eqList) {
            if(isNumber(eqPart)) {	 	
                postfixList.add(eqPart);
            }
            else if(isBrackOpen(eqPart) ) {
                stack.push(eqPart);
            }
            else if(isOperator(eqPart)  && !isBrack(eqPart) ) {
                // проверяем приоритеты текущего оператора и оператора из стека
                // пока приоритет операции из стека больше или равен считываем из стека
                int priorityInStack = -1;
                int priorityInExp = getPriority(eqPart);
                if(!stack.isEmpty()) {
                    priorityInStack = getPriority(stack.peek());
                }
                while (!stack.isEmpty()  && (priorityInStack  >=  priorityInExp) )  {
                    // если в стеке открывающаяся скобка, не заносим его в постфиксное выражение
                    if(!isBrackOpen(stack.peek()) ) {
                        postfixList.add( stack.pop() );
                    }
                    priorityInExp = parser.operators.getPriority(eqPart);
                    if(!stack.isEmpty() ) {
                        priorityInStack = parser.operators.getPriority(stack.peek());
                    }
                }
                // добавляем оператор в постфиксное выражение
                stack.push(eqPart);
            }


            // Если закрывающаяся скобка, то считываем значения пока не обнаружим открывающуюся скобку
            else if( isBrackClosed(eqPart) ) {
                while( !stack.isEmpty()  &&  !isBrackOpen(stack.peek()) ) {
                    postfixList.add(stack.pop() );
                }
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }

        // Считываем из стека все, что осталось
        while(!stack.isEmpty() ) {
            postfixList.add(stack.pop() );
        }
        return postfixList;
    }

    
    int getPriority(String exp){
    	return parser.operators.getPriority(exp) ;
    }
    
    
	@Override
	public boolean isNumber(String exp) {
		return parser.isNumber(exp);
	}

	@Override
	public boolean isBrackOpen(String exp) {
		return parser.isBrackOpen(exp);
	}

	@Override
	public boolean isBrackClosed(String exp) {
		return parser.isBrackClosed(exp);
	}

	@Override
	public boolean isBrack(String exp) {
		return parser.isBrack(exp);
	}

	@Override
	public boolean isSum(String exp) {
		return parser.isSum(exp);
	}

	@Override
	public boolean isSub(String exp) {
		return parser.isSub(exp);
	}

	@Override
	public boolean isMult(String exp) {
		return parser.isMult(exp);
	}

	@Override
	public boolean isDiv(String exp) {
		return parser.isDiv(exp);
	}

	@Override
	public boolean isSin(String exp) {
		return parser.isSin(exp);
	}

	@Override
	public boolean isUnary(String exp) {
		return parser.isUnary(exp);
	}

	@Override
	public boolean isBinary(String exp) {
		return parser.isBinary(exp);
	}

	@Override
	public boolean isMathOps(String exp) {
		return parser.isMathOps(exp);
	}

	@Override
	public boolean isOperator(String exp) {
		return parser.isOperator(exp);
	}

    
}