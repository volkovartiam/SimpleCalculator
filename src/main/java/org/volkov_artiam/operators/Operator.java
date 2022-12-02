package org.volkov_artiam.operators;

public interface Operator {

    String getPattern();
    boolean isFindedWithPatternIn(String exp);
    int getPriority();

}
