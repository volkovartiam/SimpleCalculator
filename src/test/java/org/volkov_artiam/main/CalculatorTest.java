package org.volkov_artiam.main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void testConverterCaseOne() {
        String input = "123+123.123" ;
        Calculator calculator = new Calculator();
        calculator.calculate(input);
        Assertions.assertEquals("246.123", calculator.getAnswer());
    }

    @Test
    void testConverterCaseTwo() {
        String input = "1+1+10*(12-12+1-1*(10+5))" ;
        //2  +10*(0    +1-15)
        //2  +10*(-14)
        //2  -140
        //-138
        Calculator calculator = new Calculator();
        calculator.calculate(input);
        Assertions.assertEquals("-138.0", calculator.getAnswer());
    }

    @Test
    void testConverterCaseThree() {
        String input = "1/1-3+3*(10+9-(12+12)*10/(10-9))*(100/50)" ;
        //1  -3+3*(19  -(24   )*10/   1  )* 2
        //1  -3+3*(19  -240) *2
        //1  -3+3*(-221)*2
        //1  -3-1326
        Calculator calculator = new Calculator();
        calculator.calculate(input);
        Assertions.assertEquals("-1328.0", calculator.getAnswer());
    }

}

