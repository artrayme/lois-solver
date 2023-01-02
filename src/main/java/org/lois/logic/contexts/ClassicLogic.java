package org.lois.logic.contexts;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.lois.logic.domain.Logic;
import org.lois.logic.domain.Value;

//TODO: check size of value arrays
public class ClassicLogic implements Logic {
    private Value applyUnary(Value value, Function<Integer, Integer> function) {
        var decimal = value.getDecimal();
        return Value.of(function.apply(decimal));
    }

    private Value applyBinary(Value left, Value right, BiFunction<Integer, Integer, Integer> biFunction) {
        var l = left.getDecimal();
        var r = right.getDecimal();
        return Value.of(biFunction.apply(l, r));
    }

    @Override
    public Value not(Value value) {
        return applyUnary(value, decimal -> decimal == 0 ? 1 : 0);
    }

    @Override
    public Value and(Value left, Value right) {
        return applyBinary(left, right, Math::min);
    }

    @Override
    public Value or(Value left, Value right) {
        return applyBinary(left, right, Math::max);
    }

    @Override
    public Value impl(Value left, Value right) {
        //return applyBinary(left, right, (l, r) -> l >);
        return null;
    }

    @Override
    public Value diamond(Value left, Value right) {
        return null;
    }

    @Override
    public Value square(Value left, Value right) {
        return null;
    }
}
