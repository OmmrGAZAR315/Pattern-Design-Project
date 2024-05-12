package org.example.dpproject.app.Helpers;

public enum ParametersType {
    required,
    optional;

    public boolean isRequired() {
        return this == required;
    }
    public boolean isOptional() {
        return this == required;
    }
}
