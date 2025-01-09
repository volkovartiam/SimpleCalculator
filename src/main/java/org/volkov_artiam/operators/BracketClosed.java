package org.volkov_artiam.operators;

public class BracketClosed extends Operator{

    public BracketClosed() {
    	super.setPattern("\\)");
    	super.setPriority(0);
    }
}

