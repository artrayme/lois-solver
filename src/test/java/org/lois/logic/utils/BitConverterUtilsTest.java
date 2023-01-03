package org.lois.logic.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BitConverterUtilsTest {

    @Test
    public void testConversionToTripletLogic(){
        assertArrayEquals(BitConverterUtils.toTripletLogicBits(0), new boolean[]{false, false});
        assertArrayEquals(BitConverterUtils.toTripletLogicBits(1), new boolean[]{false, true});
        assertArrayEquals(BitConverterUtils.toTripletLogicBits(2), new boolean[]{true, true});
    }
}
