package org.lois.logic.logics;

import org.lois.logic.domain.Value;

public class FourDigitLogic extends BitByBitLogic {
    @Override
    protected Value newFalse() {
        return Value.of(new boolean[]{false, false});
    }

    @Override
    protected Value newTrue() {
        return Value.of(new boolean[]{true, true});
    }
}
