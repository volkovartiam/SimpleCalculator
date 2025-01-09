package org.volkov_artiam.operators;

public class Mult extends Operator{

    public Mult() {
    	super.setPattern("\\*");
    	super.setPriority(3);
    }

}
