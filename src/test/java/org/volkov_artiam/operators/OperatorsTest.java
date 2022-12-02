package org.volkov_artiam.operators;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class OperatorsTest {

    Operators ops = new Operators();

    @Test
    void testIsNumberTrue() {

        assertTrue(ops.isNumber("123.") );
        assertTrue(ops.isNumber("123+") );
        assertTrue(ops.isNumber("123.4564") );
    }

    @Test
    void testIsNotNumberTrue() {
        assertFalse(ops.isNumber("opo") );
    }


    @Test
    void testIsBrackOpenTrue() {
        assertTrue(ops.isBrackOpen("(") );
        assertTrue(ops.isBrackOpen("(123+") );
    }

    @Test
    void testIsNotBrackOpenTrue() {
        assertFalse(ops.isBrackOpen("123+") );
    }


    @Test
    void testGetOperatorsPatternsList() {
        ArrayList<String> actualPatternsList = new ArrayList<>();
        actualPatternsList.add("\\d+\\.\\d+");		// NumberFractional
        actualPatternsList.add("\\d+");				// Number

        actualPatternsList.add("\\(");				// BracketOpened
        actualPatternsList.add("\\)");				// BracketClosed

        actualPatternsList.add("\\+");				// Sum
        actualPatternsList.add("\\-");				// Sub
        actualPatternsList.add("\\*");				// Mult
        actualPatternsList.add("\\/");				// Div

        actualPatternsList.add("sin");				// Sin

        ArrayList<String> expectedPatternsList = ops.getOperatorsPatternsList() ;
        assertEquals(expectedPatternsList, actualPatternsList);
    }

    @Test
    void testIsUnary() {
        assertTrue( ops.isUnary("sin") );
    }

    void testIsNotUnary() {
        assertFalse( ops.isUnary("+") );
        assertFalse( ops.isUnary("-") );
        assertFalse( ops.isUnary("*") );
        assertFalse( ops.isUnary("/") );
    }

    @Test
    void testPriorityCheck() {
        assertTrue( ops.getPriority("+") ==  ops.getPriority("-") );
        assertTrue( ops.getPriority("*") ==  ops.getPriority("/") );

        assertTrue( ops.getPriority("*") >  ops.getPriority("+") );

        assertTrue( ops.getPriority("sin") >  ops.getPriority("+") );
        assertTrue( ops.getPriority("sin") >  ops.getPriority("*") );
    }

}