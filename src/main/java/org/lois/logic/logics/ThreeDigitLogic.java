package org.lois.logic.logics;

import java.util.Arrays;

import org.lois.logic.domain.Value;

public final class ThreeDigitLogic extends BitByBitLogic {
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
        var array = value.getArrayCopy();
        if (Arrays.equals(array, new boolean[]{false, true})) {
            return Value.of(array);
        } else {
            return super.not(value);
        }
    }
}
