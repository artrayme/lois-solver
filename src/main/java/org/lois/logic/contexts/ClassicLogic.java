package org.lois.logic.contexts;

import org.lois.logic.domain.Value;

public class ClassicLogic extends AbstractLogic {
    @Override
    protected Value newFalse() {
        return Value.of(new boolean[]{false});
    }

    @Override
    protected Value newTrue() {
        return Value.of(new boolean[]{true});
    }
}
