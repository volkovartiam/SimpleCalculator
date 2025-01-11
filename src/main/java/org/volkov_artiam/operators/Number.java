package org.volkov_artiam.operators;

public class Number extends Operator{

    public Number() {
    	super.setPattern("\\d+");
    	super.setPriority(10);
    }
    
}