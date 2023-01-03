package org.lois.logic.utils;

import org.junit.jupiter.api.Test;
import org.lois.logic.domain.Value;

import static org.junit.jupiter.api.Assertions.*;

public class BitConverterUtilsTest {

    @Test
    public void testConversionToTripletLogic(){
        assertEquals(BitConverterUtils.toTripletLogicBits(0), Value.of(new boolean[]{false, false}));
        assertEquals(BitConverterUtils.toTripletLogicBits(1), Value.of(new boolean[]{false, true}));
        assertEquals(BitConverterUtils.toTripletLogicBits(2), Value.of(new boolean[]{true, true}));
    }
}
