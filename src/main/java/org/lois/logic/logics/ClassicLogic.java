package org.lois.logic.logics;

import org.lois.logic.domain.Value;

/**
 * Classic boolean logic
 */
public final class ClassicLogic extends BitByBitLogic {
    @Override
    protected Value newFalse() {
        return Value.of(new boolean[]{false});
    }

    @Override
    protected Value newTrue() {
        return Value.of(new boolean[]{true});
    }

    @Override
    public Value diamond(Value value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value square(Value value) {
        throw new UnsupportedOperationException();
    }
}
