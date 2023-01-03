package org.lois.logic.table.utils;

import org.lois.logic.domain.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ClassicLogicStateGenerator implements StateGenerator {
    private final AtomicInteger currentState = new AtomicInteger();
    private final int varsCount;

    public ClassicLogicStateGenerator(int varsCount) {
        this.varsCount = varsCount;
    }


    @Override
    public List<Value> getCurrent() {
        boolean[] booleans = new boolean[varsCount];
        for (int i = varsCount-1; i >= 0; i--) {
            booleans[i] = ((currentState.get() & (1 << i)) != 0);
        }

        List<Value> result = new ArrayList<>();
        for (int i = 0; i < varsCount; i++) {
            result.add(Value.of(new boolean[]{booleans[i]}));
        }
        return result;
    }

    @Override
    public List<Value> incrementAndGet() {
        currentState.incrementAndGet();
        return getCurrent();
    }

    @Override
    public void reset() {
        currentState.set(0);
    }

    @Override
    public boolean hasNext() {
        return currentState.get() < Math.pow(2, varsCount);
    }
}
