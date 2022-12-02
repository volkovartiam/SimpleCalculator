package org.volkov_artiam.operators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NumberFractionalTest {

    NumberFractional number = new NumberFractional();

    @Test
    void testFalse() {
        assertFalse(number.isFindedWithPatternIn(".") );
        assertFalse(number.isFindedWithPatternIn("123+123") );
        assertFalse(number.isFindedWithPatternIn(".123") );
    }

    @Test
    void testTrue() {
        assertTrue(number.isFindedWithPatternIn("123.123") );
        assertTrue(number.isFindedWithPatternIn("0.123") );
    }
}

