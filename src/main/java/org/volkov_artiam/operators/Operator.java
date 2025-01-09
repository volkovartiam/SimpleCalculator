package org.volkov_artiam.operators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Operator{

    String pattern;
    int priority;
	
    String getPattern() {
    	return pattern;
    }
    void setPattern(String pattern) {
    	this.pattern = pattern;
    }
    
    void setPriority(int priority) {
    	this.priority = priority;
    }

    int getPriority() {
    	return priority;
    }
        
    boolean isFindedWithPatternIn(String exp) {
        Pattern pat = Pattern.compile(pattern);
        Matcher matcher = pat.matcher(exp);
        return matcher.find();
    }
    
    


}
