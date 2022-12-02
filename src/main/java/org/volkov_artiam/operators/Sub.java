package org.volkov_artiam.operators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sub implements Operator{

    String pattern = "\\-";
    int priority = 1;

    public boolean isFindedWithPatternIn(String exp) {
        Pattern pat = Pattern.compile(pattern);
        Matcher matcher = pat.matcher(exp);
        return matcher.find();
    }

    public int getPriority() {
        return priority;
    }

    public String getPattern() {
        return pattern;
    }
}
