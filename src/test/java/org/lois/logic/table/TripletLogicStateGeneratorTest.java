package org.lois.logic.table;

import org.junit.jupiter.api.Test;
import org.lois.logic.domain.Value;
import org.lois.logic.table.utils.TripletLogicStateGenerator;
import org.lois.logic.utils.BitConverterUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TripletLogicStateGeneratorTest {

    @Test
    public void genStateTest(){
        var stateGenerator = new TripletLogicStateGenerator(2);
        List<Value> next1 = stateGenerator.getCurrent();
        assertEquals(next1.get(0), Value.of(BitConverterUtils.toTripletLogicBits(0)));
        assertEquals(next1.get(1), Value.of(BitConverterUtils.toTripletLogicBits(0)));
        assertEquals(stateGenerator.incrementAndGet().get(1), Value.of(BitConverterUtils.toTripletLogicBits(1)));
        assertEquals(stateGenerator.incrementAndGet().get(1), Value.of(BitConverterUtils.toTripletLogicBits(2)));

        assertTrue(stateGenerator.hasNext());

        List<Value> next2 = stateGenerator.incrementAndGet();
        assertEquals(next2.get(0), Value.of(BitConverterUtils.toTripletLogicBits(1)));
        assertEquals(next2.get(1), Value.of(BitConverterUtils.toTripletLogicBits(0)));
        assertEquals(stateGenerator.incrementAndGet().get(1), Value.of(BitConverterUtils.toTripletLogicBits(1)));
        assertEquals(stateGenerator.incrementAndGet().get(1), Value.of(BitConverterUtils.toTripletLogicBits(2)));

        assertTrue(stateGenerator.hasNext());

        List<Value> next3 = stateGenerator.incrementAndGet();
        assertEquals(next3.get(0), Value.of(BitConverterUtils.toTripletLogicBits(2)));
        assertEquals(next3.get(1), Value.of(BitConverterUtils.toTripletLogicBits(0)));
        assertEquals(stateGenerator.incrementAndGet().get(1), Value.of(BitConverterUtils.toTripletLogicBits(1)));
        assertEquals(stateGenerator.incrementAndGet().get(1), Value.of(BitConverterUtils.toTripletLogicBits(2)));

        assertFalse(stateGenerator.hasNext());
    }
}
