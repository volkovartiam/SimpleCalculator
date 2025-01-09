package org.volkov_artiam.operators;

public class BracketOpened extends Operator{

    public BracketOpened() {
    	super.setPattern("\\(");
    	super.setPriority(0);
    }

}
