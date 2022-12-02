package org.volkov_artiam.operators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SubTest {

    Operator sub = new Sub();

    @Test
    void testTrue() {
        assertTrue(sub.isFindedWithPatternIn("-") );
        assertTrue(sub.isFindedWithPatternIn("123-123") );
    }

    @Test
    void testFalse() {
        assertFalse(sub.isFindedWithPatternIn("+") );
    }

}
