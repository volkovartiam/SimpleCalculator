package org.volkov_artiam.operators;

public class Sum extends Operator{
   
    public Sum() {
    	super.setPattern("\\+");
    	super.setPriority(1);
    }

}

