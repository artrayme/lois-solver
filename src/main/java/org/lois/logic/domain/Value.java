package org.lois.logic.domain;

import java.util.Arrays;

@lombok.Value
public class Value {
    Integer decimal;

    public static Value of(int decimal) {
        return new Value(decimal);
    }

    /**
     * @return Value without array inside it
     */
    public static Value placeholder() {
        return new Value(null);
    }
}
