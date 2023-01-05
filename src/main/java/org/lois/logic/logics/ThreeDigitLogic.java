package org.lois.logic.logics;

import java.util.Arrays;

import org.lois.logic.domain.Value;

public final class ThreeDigitLogic extends BitByBitLogic {
    private static final boolean[] INCORRECT_MIDDLE_VALUE = new boolean[]{ true, false };

    @Override
    protected Value newFalse() {
        return Value.of(new boolean[]{false, false});
    }

    @Override
    protected Value newTrue() {
        return Value.of(new boolean[]{true, true});
    }

    @Override
    public Value not(Value value) {
        return replaceMiddleIfItIs(super.not(value));
    }

    @Override
    public Value equal(Value left, Value right) {
        return replaceMiddleIfItIs(super.equal(left, right));
    }

    @Override
    public Value implication(Value left, Value right) {
        return replaceMiddleIfItIs(super.implication(left, right));
    }

    /**
     * Replaces incorrect middle value with correct one
     */
    private Value replaceMiddleIfItIs(Value value) {
        if (Arrays.equals(INCORRECT_MIDDLE_VALUE, value.getArray())) {
            return Value.of(new boolean[]{ false, true });
        }
        else {
            return value;
        }
    }
}
