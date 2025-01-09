package org.volkov_artiam.main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void testConverterCaseOne() {
        String input = "123.0+123.123" ;
        Calculator calculator = new Calculator();
        calculator.calculate(input);
        Assertions.assertEquals("246.123", calculator.getAnswer());
    }

    @Test
    void testConverterCaseTwo() {
        String input = "1.0+1.0+10.0*(12.0-12.0+1.0-1.0*(10.0+5.0))" ;
        Calculator calculator = new Calculator();
        calculator.calculate(input);
        Assertions.assertEquals("-138.0", calculator.getAnswer());
    }

    @Test
    void testConverterCaseThree() {
        String input = "1.0/1.0-3.0+3.0*(10.0+9.0-(12.0+12.0)*10.0/(10.0-9.0))*(100.0/50.0)" ;
        Calculator calculator = new Calculator();
        calculator.calculate(input);
        Assertions.assertEquals("-1328.0", calculator.getAnswer());
    }

}

