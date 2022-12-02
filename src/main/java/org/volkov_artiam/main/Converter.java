package org.volkov_artiam.main;

import java.util.ArrayList;
import java.util.Stack;
import org.volkov_artiam.operators.Operators;

public class Converter {

    Operators ops = new Operators();
    ArrayList<String> postFixExpr = new ArrayList<>();
    ArrayList<String> eqList = new ArrayList<>();


    public void validConvertToPostFix(String exp) throws IllegalArgumentException{
        Validator validator = new Validator();
        if(validator.isValidString(exp)) {
            convertToPostFix(exp);
        } else {
            throw new IllegalArgumentException("Строка не валидна");
        }
    }


    public void convertToPostFix(String exp){
        Parser parser = new Parser();
        Stack<String> stack = new Stack<String>();

        parser.parse(exp);
        eqList = parser.getEquationList();

        for(String eqPart : eqList) {
            // Если число, то добавляем его в постфиксное выражение
            if(ops.isNumber(eqPart)) {
                postFixExpr.add(eqPart);
            }

            // Если открывающаяся скобка, то добавляем его в постфиксное выражение
            else if( ops.isBrackOpen(eqPart) ) {
                stack.push(eqPart);
            }

            // Если оператор(но не скобка)
            else if( ops.isOperator(eqPart)  && !ops.isBrack(eqPart) ) {
                // проверяем приоритеты текущего оператора и оператора из стека
                // пока приоритет операции из стека больше или равен считываем из стека
                int priorityOperatorInStack = -1;
                int priorityOperatorInExp = ops.getPriority(eqPart);
                if(!stack.isEmpty()) {
                    priorityOperatorInStack = ops.getPriority(stack.peek());
                }
                while (!stack.isEmpty()  && (priorityOperatorInStack  >=  priorityOperatorInExp) )  {
                    // если в стеке открывающаяся скобка, не заносим его в постфиксное выражение
                    if(!ops.isBrackOpen(stack.peek()) ) {
                        postFixExpr.add( stack.pop() );
                    }
                    priorityOperatorInExp = ops.getPriority(eqPart);
                    if(!stack.isEmpty() ) {
                        priorityOperatorInStack = ops.getPriority(stack.peek());
                    }
                }
                // добавляем оператор в постфиксное выражение
                stack.push(eqPart);
            }


            // Если закрывающаяся скобка, то считываем значения пока не обнаружим открывающуюся скобку
            else if( ops.isBrackClosed(eqPart) ) {
                while( !stack.isEmpty()  &&  !ops.isBrackOpen(stack.peek()) ) {
                    postFixExpr.add(stack.pop() );
                }
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }

        // Считываем из стека все, что осталось
        while(!stack.isEmpty() ) {
            postFixExpr.add(stack.pop() );
        }
    }

    public ArrayList<String> getPostFix() {
        return postFixExpr;
    }
}