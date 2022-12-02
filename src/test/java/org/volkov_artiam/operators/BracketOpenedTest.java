package org.volkov_artiam.operators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BracketOpenedTest {

    Operator bracketOpened = new BracketOpened();

    @Test
    void testTrue() {
        assertTrue(bracketOpened.isFindedWithPatternIn( "(" ));
        assertTrue(bracketOpened.isFindedWithPatternIn("123+(123-1)") );
    }

    @Test
    void testFalse() {
        assertFalse(bracketOpened.isFindedWithPatternIn("-") );
    }

}

