package org.volkov_artiam.operators;

public class Sub extends Operator{

    public Sub() {
    	super.setPattern("\\-");
    	super.setPriority(1);
    }

}
