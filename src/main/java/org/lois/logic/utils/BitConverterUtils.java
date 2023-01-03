package org.lois.logic.utils;

public class BitConverterUtils {

    public static boolean[] toTripletLogicBits(int number){
        return switch (number){
            case 0 -> new boolean[]{false, false};
            case 1 -> new boolean[]{false, true};
            case 2 -> new boolean[]{true, true};
            default -> throw new IllegalStateException("Number "+ number + " is invalid for 3-digit logic");
        };
    }

    public static boolean[] toFourDigitLogicBits(int number){
        return switch (number){
            case 0 -> new boolean[]{false, false};
            case 1 -> new boolean[]{false, true};
            case 2 -> new boolean[]{true, false};
            case 3 -> new boolean[]{true, true};
            default -> throw new IllegalStateException("Number "+ number + " is invalid for 4-digit logic");
        };
    }
}
