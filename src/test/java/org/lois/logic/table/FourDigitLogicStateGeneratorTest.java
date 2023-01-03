package org.lois.logic.table;

import org.junit.jupiter.api.Test;
import org.lois.logic.domain.Value;
import org.lois.logic.table.utils.FourDigitLogicStateGenerator;
import org.lois.logic.utils.BitConverterUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FourDigitLogicStateGeneratorTest {
    @Test
    public void genStateTest(){
        var stateGenerator = new FourDigitLogicStateGenerator(2);
        assertTrue(stateGenerator.hasNext());

        List<Value> next1 = stateGenerator.getCurrent();
        assertEquals(next1.get(0), (BitConverterUtils.toFourDigitLogicBits(0)));
        assertEquals(next1.get(1), (BitConverterUtils.toFourDigitLogicBits(0)));
        assertEquals(stateGenerator.incrementAndGet().get(1), (BitConverterUtils.toFourDigitLogicBits(1)));
        assertEquals(stateGenerator.incrementAndGet().get(1), (BitConverterUtils.toFourDigitLogicBits(2)));
        assertEquals(stateGenerator.incrementAndGet().get(1), (BitConverterUtils.toFourDigitLogicBits(3)));

        assertTrue(stateGenerator.hasNext());

        List<Value> next2 = stateGenerator.incrementAndGet();
        assertEquals(next2.get(0), (BitConverterUtils.toFourDigitLogicBits(1)));
        assertEquals(next2.get(1), (BitConverterUtils.toFourDigitLogicBits(0)));
        assertEquals(stateGenerator.incrementAndGet().get(1), (BitConverterUtils.toFourDigitLogicBits(1)));
        assertEquals(stateGenerator.incrementAndGet().get(1), (BitConverterUtils.toFourDigitLogicBits(2)));
        assertEquals(stateGenerator.incrementAndGet().get(1), (BitConverterUtils.toFourDigitLogicBits(3)));

        assertTrue(stateGenerator.hasNext());

        List<Value> next3 = stateGenerator.incrementAndGet();
        assertEquals(next3.get(0), (BitConverterUtils.toFourDigitLogicBits(2)));
        assertEquals(next3.get(1), (BitConverterUtils.toFourDigitLogicBits(0)));
        assertEquals(stateGenerator.incrementAndGet().get(1), (BitConverterUtils.toFourDigitLogicBits(1)));
        assertEquals(stateGenerator.incrementAndGet().get(1), (BitConverterUtils.toFourDigitLogicBits(2)));
        assertEquals(stateGenerator.incrementAndGet().get(1), (BitConverterUtils.toFourDigitLogicBits(3)));

        assertTrue(stateGenerator.hasNext());

        List<Value> next4 = stateGenerator.incrementAndGet();
        assertEquals(next4.get(0), (BitConverterUtils.toFourDigitLogicBits(3)));
        assertEquals(next4.get(1), (BitConverterUtils.toFourDigitLogicBits(0)));
        assertEquals(stateGenerator.incrementAndGet().get(1), (BitConverterUtils.toFourDigitLogicBits(1)));
        assertEquals(stateGenerator.incrementAndGet().get(1), (BitConverterUtils.toFourDigitLogicBits(2)));
        assertEquals(stateGenerator.incrementAndGet().get(1), (BitConverterUtils.toFourDigitLogicBits(3)));

        assertFalse(stateGenerator.hasNext());
    }
}
