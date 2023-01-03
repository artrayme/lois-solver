package org.lois.logic.table.utils;

import org.lois.logic.domain.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FourDigitLogicStateGenerator implements StateGenerator {
    private final AtomicInteger currentState = new AtomicInteger();
    private final int varsCount;

    public FourDigitLogicStateGenerator(int varsCount) {
        this.varsCount = varsCount;
    }

    @Override
    public List<Value> getCurrent() {
        boolean[] booleans = new boolean[varsCount*2];
        for (int i = varsCount*2-1; i >= 0; i--) {
            booleans[i] = ((currentState.get() & (1 << i)) != 0);
        }

        List<Value> result = new ArrayList<>();
        for (int i = varsCount*2-1; i >= 0;i-=2) {
            result.add(Value.of(new boolean[]{booleans[i], booleans[i-1]}));
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
        return currentState.get() < Math.pow(4,varsCount)-1 ;
    }
}
