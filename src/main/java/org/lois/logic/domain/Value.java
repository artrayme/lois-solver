package org.lois.logic.domain;

import java.util.Arrays;

@lombok.Value
public class Value {
    boolean[] array;

    private Value(boolean[] array) {
        this.array = array;
    }

    public boolean[] getArray() {
        if (array == null) {
            throw new NullPointerException("Value can't be used without setting array");
        }
        return array;
    }

    public boolean[] getArrayCopy() {
        if (array == null) {
            throw new NullPointerException("Value can't be used without setting array");
        }
        return Arrays.copyOf(array, array.length);
    }

    public static Value of(boolean[] array) {
        return new Value(array);
    }

    /**
     * @return Value without array inside it
     */
    public static Value placeholder() {
        return new Value(null);
    }
}
