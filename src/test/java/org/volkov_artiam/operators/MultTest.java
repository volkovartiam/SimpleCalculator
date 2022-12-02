package org.volkov_artiam.operators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MultTest {

    Operator mult = new Mult();

    @Test
    void testTrue() {
        assertTrue(mult.isFindedWithPatternIn("*") );
        assertTrue(mult.isFindedWithPatternIn("123**123") );
    }

    @Test
    void testFalse() {
        assertFalse(mult.isFindedWithPatternIn("-") );
    }

}