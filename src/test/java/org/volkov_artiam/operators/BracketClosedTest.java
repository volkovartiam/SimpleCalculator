package org.volkov_artiam.operators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BracketClosedTest {

    Operator bracketClosed = new BracketClosed();

    @Test
    void testTrue() {
        assertTrue(bracketClosed.isFindedWithPatternIn( ")" ));
        assertTrue(bracketClosed.isFindedWithPatternIn("123+(123-1)") );
    }

    @Test
    void testFalse() {
        assertFalse(bracketClosed.isFindedWithPatternIn("-") );
    }
}
