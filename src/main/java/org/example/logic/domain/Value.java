package org.example.logic.domain;

@lombok.Value
public class Value {
    boolean[] array;

    static Value of(boolean[] array) {
        return new Value(array);
    }
}
