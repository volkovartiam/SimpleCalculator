package org.volkov_artiam.main;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidatorTest {

    @Test
    void testIsValidStringWithoutUknownSymbolsTrueCase() {
        boolean hasUknownSymbols = false;
        String[] input = {  "123.123+123" ,
                "1-5",
                "(3.7/15)" ,
                "(3.7*15)",
                "sin(3.7*15/1+sin(1))",
                "sin(15)",
                "(3.7*15+12/12)",
                "sin(3.7*15)"			};
        for(String i : input) {
            Validator validator = new Validator();
            hasUknownSymbols = validator.isValidString( i );
            assertTrue(hasUknownSymbols);
        }
    }


    @Test
    void testIsValidStringWithoutUknownSymbolsFalseCase() {			// Mistake №1 имееются неизвестные символы
        boolean hasUknownSymbols = true;
        String[] input = { "!123.123+123" ,
                "tan1-5",
                "(yut3.7/15)" ,
                "(3.7*15)p",
                "arsin(3.7*15)"  };
        for(String i : input) {
            Validator validator = new Validator();
            assertThrows( IllegalArgumentException.class,
                    //hasUknownSymbols =
                    () -> validator.isValidString( i )  );
            //assertFalse(hasUknownSymbols);
        }
    }


    @Test
    void testIsValidStringWithBracketsFalseCase() {
        boolean isValidString = true;
        String[] input = { "(" , 								// Mistake №2 Ошибка в написании скобок
                ")" ,
                "(()" ,
                "())" ,
                "((12+45.45)" ,
                "(12+45.45))"  };
        for(String i : input) {
            Validator validator = new Validator();
            assertThrows( IllegalArgumentException.class,
                    //isValidString =
                    () -> validator.isValidString( i )  );
            //assertFalse(isValidString);
        }
    }


    @Test
    void testIsValidStringWithEmptyBracketsFalseCase() {
        boolean isValidString = true;
        String[] input = { "()" , 								// Mistake №3 выражение состоит из пустых скобок
                "(())",
                "(12+5+(()))"  };
        for(String i : input) {
            Validator validator = new Validator();
            assertThrows( IllegalArgumentException.class,
                    //isValidString =
                    () -> validator.isValidString( i )  );
            //assertFalse(isValidString);
        }
    }


    @Test
    void testIsValidStringWithTwoBinaryOperatorsFalseCase() {
        boolean isValidString = true;
        String[] input = { 	"(1+-5)", 							// "Mistake 4 два бинарных оператора рядом"
                "1+/8" 	,
                "1++8" 	,
                "1--8" 	,
                "1**8" 	,
                "1//1" 	,
                "1*/8" 	 	};
        for(String i : input) {
            Validator validator = new Validator();
            assertThrows( IllegalArgumentException.class,
                    //isValidString =
                    () -> validator.isValidString( i )  );
            //assertFalse(isValidString);
        }
    }


    @Test
    void testIsValidStringWithoutOpsBetweenNumbersAndBracketsFalseCase() {
        boolean isValidString = true;
        String[] input = { 	"3.7(1+2)" , 						// "Mistake 5 отсутствие символа между числом и скобкой"  );
                "(3.7(1+2))" 	};
        for(String i : input) {
            Validator validator = new Validator();
            assertThrows( IllegalArgumentException.class,
                    //isValidString =
                    () -> validator.isValidString( i )  );
            //assertFalse(isValidString);
        }
    }


    @Test
    void testIsValidStringWithoutOpsBetweenBracketsAndNumbersAndFalseCase() {
        boolean isValidString = true;
        String[] input = { 	"(1+2)3.7" , 						// "Mistake 6 отсутствие символа между скобкой и числом"  );
                "((1+2)3.7)" 	};
        for(String i : input) {
            Validator validator = new Validator();
            assertThrows( IllegalArgumentException.class,
                    //isValidString =
                    () -> validator.isValidString( i )  );
            //assertFalse(isValidString);
        }
    }


    @Test
    void testIsValidStringWithNumbersAndOperatorsInBracketsFalseCase() {
        boolean isValidString = true;
        String[] input = { 	"(+)" , 						// Mistake №7 число или оператор в скобке
                "(*)" ,
                "12+(3)+45" ,
                "(1)" 			};
        for(String i : input) {
            Validator validator = new Validator();
            assertThrows( IllegalArgumentException.class,
                    //isValidString =
                    () -> validator.isValidString( i )  );
            //assertFalse(isValidString);
        }
    }


    @Test
    void testIsValidStringWithBinaryOperatorsEndsBrackets() {
        boolean isValidString = true;
        String[] input = { 	"(5+)" , 						// Mistake №8.1 бинарный оператор заканчивается скобкой
                "+)" ,
                "sin(5/)" ,
                "(5-)" 	,
                "(1+)" 		};
        for(String i : input) {
            Validator validator = new Validator();
            assertThrows( IllegalArgumentException.class,
                    //isValidString =
                    () -> validator.isValidString( i )  );
            //assertFalse(isValidString);
        }
    }

    @Test
    void testIsValidStringWithBinaryOperatorsEnds() {
        boolean isValidString = true;
        String[] input = { 	"(5+1)+" , 						//"Mistake №8.2 бинарный оператор заканчивается без цифры"
                "1+" ,
                "sin(5+1)+" ,
                "5-6+" 	,
                "1+" 		};
        for(String i : input) {
            Validator validator = new Validator();
            assertThrows( IllegalArgumentException.class,
                    //isValidString =
                    () -> validator.isValidString( i )  );
            //assertFalse(isValidString);
        }
    }


    @Test
    void testIsValidStringWithUnaryOperatorsEndsBrackets() {
        boolean isValidString = true;
        String[] input = { 	"sin5" , 						// Mistake №9 синус не со скобкой
                "sin5+5" ,
                "sin+5" 	};
        for(String i : input) {
            Validator validator = new Validator();
            assertThrows( IllegalArgumentException.class,
                    //isValidString =
                    () -> validator.isValidString( i )  );
            //assertFalse(isValidString);
        }
    }


    @Test
    void testIsValidStringWithNumberAndUnaryOperatorsWithoutBinaryOperators() {
        boolean isValidString = true;
        String[] input = { 	"4sin(3.7)" , 					// Mistake №10 число и сразу унарный оператор
                "5sin(3.7)+5" ,
                "1+1sin(5)" 	};
        for(String i : input) {
            Validator validator = new Validator();
            assertThrows( IllegalArgumentException.class,
                    //isValidString =
                    () -> validator.isValidString( i )  );
            //assertFalse(isValidString);
        }
    }


    @Test
    void testIsValidStringWithBynatyOpsWithUnaryOps() {
        boolean isValidString = true;
        String[] input = { 	"+sin(3.7)" , 					// Mistake №11 оператор рядом с унарным оператором в начале строки"
                "-sin(3.7)+5" ,
                "/sin(3.7)+5" ,
                "*sin(5)-1" 	};
        for(String i : input) {
            Validator validator = new Validator();
            assertThrows( IllegalArgumentException.class,
                    //isValidString =
                    () -> validator.isValidString( i )  );
            //assertFalse(isValidString);
        }
    }


    @Test
    void testIsValidStringWithBynatyOpsWithUnaryOpsAfterBracket() {
        boolean isValidString = true;
        String[] input = { 	"(+sin(3.7))" , 				// Mistake №12 оператор рядом с унарным оператором после скобки"  );
                "(-sin(3.7)+5)" ,
                "(/sin(3.7)+5)" ,
                "(*sin(5)-1)" 	};
        for(String i : input) {
            Validator validator = new Validator();
            assertThrows( IllegalArgumentException.class,
                    //isValidString =
                    () -> validator.isValidString( i )  );
            //assertFalse(isValidString);
        }
    }



    @Test
    void testIsValidString() {
        boolean isValidString = true;
        String[] input = { 	"(1+sin" , 						// Mistake
                "(1+sin)" ,
                // "(/sin(3.7)+5)" ,

                "1*sin" 	};
        //		"1*sin(12)" 	};
        for(String i : input) {
            Validator validator = new Validator();
            assertThrows( IllegalArgumentException.class,
                    //isValidString =
                    () -> validator.isValidString( i )  );
            //assertFalse(isValidString);;
        }
    }



    @Test
    void isEmpty() {
        String input = "";
        Validator validator = new Validator();
        assertThrows( IllegalArgumentException.class, ()-> validator.isValidString(input) );
    }

    @Test
    void isNull() {
        String input = null;
        Validator validator = new Validator();
        assertThrows( NullPointerException.class, ()-> validator.isValidString(input) );
    }

    @Test
    void testIsAllBracketsTrueCase() {
        boolean hasValidBrackets = false;
        String[] input = { "23.123+123" , "1-5", "(3.7/15)" , "(3.7*15)", "sin(3.7*15)" };

        for(String i : input) {
            Validator validator = new Validator();
            hasValidBrackets = validator.isValidString( i );
            assertTrue(hasValidBrackets);
        }
    }


    @Test
    void testIsAllBracketsFalseCase() {
        boolean hasValidBrackets = false;
        String[] input = { "123.123+123(" , "1-5)", "(3.7/15)(" , "(3.7*15))", "(sin(3.7*15)" };

        for(String i : input) {
            Validator validator = new Validator();
            assertThrows( IllegalArgumentException.class,
                    //isValidString =
                    () -> validator.isValidString( i )  );
            //assertFalse(isValidString);
        }
    }
}