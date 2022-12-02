package org.volkov_artiam.main;

import java.util.ArrayList;
import org.volkov_artiam.operators.Operators;

class Validator {

    Operators ops = new Operators();
    Parser parser = new Parser();

    ArrayList<String> eqList;
    ArrayList<String> unknownSymbols;

    // Проверка правильности записи операторов
    public boolean isValidString(String exp ) throws IllegalArgumentException {

        parser.isNotNullAndNotEmpty(exp);
        parser.parse(exp);

        eqList = parser.getEquationList();
        unknownSymbols = parser.getUknownSymbolsList();

        // Проверка наличия неизвестных символов
        if(unknownSymbols.size() > 0) {
            //System.out.println( "Mistake №1 имееются неизвестные символы" );
            throw new IllegalArgumentException("Mistake №1 имееются неизвестные символы");
            //return false;
        }

        // Проверка написания скобок
        if(!isAllBrackets(eqList )) {
            //System.out.println( "Mistake №2 Ошибка в написании скобок" );
            throw new IllegalArgumentException("Mistake №2 Ошибка в написании скобок");
            //return false;
        }

        for(int i = 0; i < eqList.size(); i++ ) {
            String previous = "";
            String prev_prev = "";
            String current = "";
            String next = "";

            current = eqList.get(i);
            if(i + 1 < eqList.size() ) {
                next = eqList.get(i + 1);
            }

            if(i - 1 >= 0) {
                previous = eqList.get(i -1);
            }

            if(i - 2 >= 0) {
                prev_prev = eqList.get(i - 2);
            }

            // выражение состоит из пустых скобок
            if(ops.isBrackOpen(current) & ops.isBrackClosed(next)) {
                //System.out.println("Mistake №3 выражение состоит из пустых скобок"  );
                throw new IllegalArgumentException("Mistake №3 выражение состоит из пустых скобок"  );
                //return false;
            }

            // два бинарных оператора рядом
            if(ops.isBinary(current) & ops.isBinary(next) )  {
                //System.out.println("Mistake №4 два бинарных оператора рядом"  );
                throw new IllegalArgumentException("Mistake №4 два бинарных оператора рядом"  );
                //return false;
            }

            //отсутствие символа между числом и скобкой 4(
            if(ops.isNumber(current) & ops.isBrackOpen(next) ) {
                //System.out.println("Mistake №5 отсутствие символа между числом и скобкой"  );
                throw new IllegalArgumentException("Mistake №5 отсутствие символа между числом и скобкой"  );
                //return false;
            }

            //отсутствие символа между скобкой и числом )4
            if(ops.isBrackClosed(current) & ops.isNumber(next) ) {
                //System.out.println("Mistake №6 отсутствие символа между скобкой и числом"  );
                throw new IllegalArgumentException("Mistake №6 отсутствие символа между скобкой и числом"  );
                //return false;
            }

            // число или оператор в скобке
            if( !ops.isUnary(prev_prev) &  ops.isBrackOpen(previous) & ( ops.isMathOps(current) )  & ops.isBrackClosed(next) ) {
                //System.out.println("Mistake №7 число или оператор в скобке"  );
                throw new IllegalArgumentException("Mistake №7 число или оператор в скобке"  );
                //return false;
            }

            // бинарный оператор заканчивается скобкой
            if( ops.isBinary(current)  &  ops.isBrackClosed(next)  ) {
                //System.out.println("Mistake №8 бинарный оператор заканчивается скобкой"  );
                throw new IllegalArgumentException("Mistake №8.1 бинарный оператор заканчивается скобкой"  );
                //return false;
            }

            // бинарный оператор заканчивается в конце выражения
            if( ops.isBinary(current)  & (i == eqList.size() - 1)    ) {
                //System.out.println("Mistake №8 бинарный оператор заканчивается скобкой"  );
                throw new IllegalArgumentException("Mistake №8.2 бинарный оператор заканчивается без цифры"  );
                //return false;
            }


            // синус не со скобкой
            if( ops.isSin(current)  &  !ops.isBrackOpen(next) ) {
                //System.out.println("Mistake №9 синус не со скобкой"  );
                throw new IllegalArgumentException("Mistake №9 синус не со скобкой"  );
                //return false;
            }

            // число и сразу унарный оператор
            if( ops.isNumber(current)  &  ops.isUnary(next) ) {
                //System.out.println("Mistake №10 число и сразу унарный оператор"  );
                throw new IllegalArgumentException("Mistake №10 число и сразу унарный оператор"  );
                //return false;
            }

            // оператор рядом с унарным оператором в начале строки
            if( ops.isBinary(current) & ops.isUnary(next) & (i == 0)   ) {
                //System.out.println("Mistake №11 оператор рядом с унарным оператором в начале строки"  );
                throw new IllegalArgumentException("Mistake №11 оператор рядом с унарным оператором в начале строки"  );
                //return false;
            }

            // оператор рядом с унарным оператором после открывающейся скобки
            if( ops.isBinary(current) & ops.isUnary(next) & ops.isBrackOpen(previous)  ) {
                //System.out.println("Mistake №12 оператор рядом с унарным оператором после скобки"  );
                throw new IllegalArgumentException("Mistake №12 оператор рядом с унарным оператором после скобки"  );
                //return false;
            }
        }
        return true;
    }

    // Проверка правильности записи скобок
    private boolean isAllBrackets(ArrayList<String> eqList )  {
        int countOpen = 0;
        int countClosed = 0;

        for(String eqPart : eqList) {
            if(ops.isBrackOpen(eqPart)) {
                countOpen++;
            } else if(ops.isBrackClosed(eqPart)  ) {
                countClosed++;
            }
        }
        return countOpen==countClosed;
    }


    public ArrayList<String> getEquationList(){
        return eqList;
    }

}
