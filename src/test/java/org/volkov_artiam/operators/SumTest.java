package org.volkov_artiam.operators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SumTest {

    Operator sum = new Sum();

    @Test
    void testTrue() {
        assertTrue(sum.isFindedWithPatternIn("+") );
        assertTrue(sum.isFindedWithPatternIn("123+123") );
    }

    @Test
    void testFalse() {
        assertFalse(sum.isFindedWithPatternIn("-") );
    }
}
