package org.volkov_artiam.operators;

public class NumberFractional extends Operator{

    public NumberFractional() {
    	super.setPattern("\\d+\\.\\d+");
    	super.setPriority(10);
    }
    
}