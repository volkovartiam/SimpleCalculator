package org.volkov_artiam.operators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NumberTest {

    Operator number = new Number();

    @Test
    void testTrue() {
        assertTrue(number.isFindedWithPatternIn("123") );
    }

    @Test
    void testFalse() {
        assertFalse(number.isFindedWithPatternIn(".") );
    }

}
