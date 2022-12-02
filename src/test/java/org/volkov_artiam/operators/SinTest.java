package org.volkov_artiam.operators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SinTest {

    Operator sin = new Sin();

    @Test
    void testTrue() {
        assertTrue(sin.isFindedWithPatternIn("sin") );
        assertTrue(sin.isFindedWithPatternIn("123-sin()") );
        assertTrue(sin.isFindedWithPatternIn("123-arcsin()") );
    }

    @Test
    void testFalse() {
        assertFalse(sin.isFindedWithPatternIn("+") );
    }
}