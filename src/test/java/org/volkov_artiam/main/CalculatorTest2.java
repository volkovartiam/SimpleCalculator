package org.volkov_artiam.main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest2 {

    @Test
    void testSinAndCos() {
        String input = "sin(0)+cos(90)" ;			
        Calculator calculator = new Calculator();
        calculator.calculate(input);
        Assertions.assertEquals("0.0", calculator.getAnswer());
    }

    @Test
    void testSinAndCos_2() {
        String input = "sin(90)+cos(0)" ;			
        Calculator calculator = new Calculator();
        calculator.calculate(input);
        Assertions.assertEquals("2.0", calculator.getAnswer());
    }
    
    @Test
    void testExpession() {
        String input = "1.+1+10.0*(12.0-12.0+1-1.0*(10.+5))" ;
        Calculator calculator = new Calculator();
        calculator.calculate(input);
        Assertions.assertEquals("-138.0", calculator.getAnswer());
    }

    @Test
    void testExpession_2() {
        String input = "1./1-3.+3*(10.00+9.-(12.000+12.0)*10.000/(10-9.))*(100./50.0)" ;
        Calculator calculator = new Calculator();
        calculator.calculate(input);
        Assertions.assertEquals("-1328.0", calculator.getAnswer());
    }

    @Test
    void testExpession_3() {
        String input = "1.+1+10.0*(12.0-12.0+1-1.0*(10.+5)) +sin(0) +cos(90)" ;
        Calculator calculator = new Calculator();
        calculator.calculate(input);
        Assertions.assertEquals("-138.0", calculator.getAnswer());
    }

    @Test
    void testExpession_4() {
        String input = "1.+1+10.0*(12.0-1.*(10.+5)) +sin(0) +cos(90)" ;
        Calculator calculator = new Calculator();
        calculator.calculate(input);
        Assertions.assertEquals("-28.0", calculator.getAnswer());
    }
    
}

