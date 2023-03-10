package org.lois.logic.logics;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.lois.logic.domain.Logic;
import org.lois.logic.domain.Value;

//TODO: check size of value arrays
/**
 * Logic implementation with bit by bit operations of classic logic
 */
abstract class BitByBitLogic implements Logic {
    protected abstract Value newFalse();

    protected abstract Value newTrue();

    protected Value unaryOperation(Value value, Function<Boolean, Boolean> function) {
        return Value.of(iterateOver(value.getArray(), function));
    }

    protected boolean[] iterateOver(boolean[] array, Function<Boolean, Boolean> function) {
        var result = Arrays.copyOf(array, array.length);
        for (int i = 0; i < result.length; i++) {
            result[i] = function.apply(array[i]);
        }
        return result;
    }

    protected Value binaryOperation(Value left, Value right, BiFunction<Boolean, Boolean, Boolean> biFunction) {
        return Value.of(iterateOver(left.getArray(), right.getArray(), biFunction));
    }

    protected boolean[] iterateOver(boolean[] left, boolean[] right, BiFunction<Boolean, Boolean, Boolean> biFunction) {
        var result = Arrays.copyOf(left, left.length);
        for (int i = 0; i < result.length; i++) {
            result[i] = biFunction.apply(left[i], right[i]);
        }
        return result;
    }

    @Override
    public Value not(Value value) {
        return unaryOperation(value, b -> !b);
    }

    @Override
    public Value and(Value left, Value right) {
        return binaryOperation(left, right, (l, r) -> l && r);
    }

    @Override
    public Value or(Value left, Value right) {
        return binaryOperation(left, right, (l, r) -> l || r);
    }

    @Override
    public Value equal(Value left, Value right) {
        var l = left.getArray();
        var r = right.getArray();

        var result = left.getArrayCopy();
        for (int i = 0; i < l.length; i++) {
            result[i] = l[i] == r[i];
        }
        return Value.of(result);
    }

    @Override
    public Value implication(Value left, Value right) {
        return binaryOperation(left, right, (l, r) -> !l || r);
    }

    @Override
    public Value diamond(Value value) {
        var array = value.getArray();

        for (var bit : array) {
            if (bit) {
                return newTrue();
            }
        }
        return newFalse();
    }

    @Override
    public Value square(Value value) {
        var array = value.getArray();

        for (var bit : array) {
            if (!bit) {
                return newFalse();
            }
        }
        return newTrue();
    }
}
