package org.volkov_artiam.operators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DivTest {

    Operator div = new Div();

    @Test
    void testTrue() {
        assertTrue(div.isFindedWithPatternIn("/") );
        assertTrue(div.isFindedWithPatternIn("123/123") );
    }

    @Test
    void testFalse() {
        assertFalse(div.isFindedWithPatternIn("-") );
    }

}
