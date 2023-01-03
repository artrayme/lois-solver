package org.lois.logic.table.utils;

import org.lois.logic.domain.Value;
import org.lois.logic.utils.BitConverterUtils;

import java.util.ArrayList;
import java.util.List;

public class TripletLogicStateGenerator implements StateGenerator {
    
    private final int varsCount;
    private List<Integer> currentState;

    public TripletLogicStateGenerator(int varsCount) {
        this.varsCount = varsCount;
        reset();
    }

    @Override
    public List<Value> incrementAndGet() {
        currentState.set(varsCount -1, currentState.get(varsCount -1)+1);
        for (int i = currentState.size()-1; i >=0 ; i--) {
            if (currentState.get(i) > 2) {
                currentState.set(i-1, currentState.get(i-1)+1);
                currentState.set(i, 0);
            }
        }

        return getCurrent();
    }

    @Override
    public List<Value> getCurrent() {
        var result = new ArrayList<Value>();
        for (Integer operandValue : currentState) {
            result.add(BitConverterUtils.toTripletLogicBits(operandValue));
        }
        return result;
    }

    @Override
    public void reset() {
        currentState = new ArrayList<>(varsCount);
        for (int i = 0; i < varsCount; i++) {
//            ToDo use standard method from Logic
            currentState.add(0);
        }
    }

    @Override
    public boolean hasNext() {
        for (Integer state : currentState) {
            if (state!=2) {
                return true;
            }
        }
        return false;
    }
}
