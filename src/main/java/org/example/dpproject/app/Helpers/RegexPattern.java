package org.example.dpproject.app.Helpers;

public enum RegexPattern {
    ID("^[1-9]\\d*$"),
    USERNAME("^[a-zA-Z0-9_]{4,16}$"),
    PASSWORD("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{4,}$"),
    NAME("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$"),
    AGE("^(?:[1-9][0-9]?|1[01][0-9]|120)$");


    private final String pattern;

    RegexPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
