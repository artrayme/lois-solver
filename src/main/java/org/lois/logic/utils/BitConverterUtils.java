package org.lois.logic.utils;

import org.lois.logic.domain.Value;

import java.util.List;

public class BitConverterUtils {

    public static Value toTripletLogicBits(int number){
        return switch (number){
            case 0 -> Value.of(new boolean[]{false, false});
            case 1 -> Value.of(new boolean[]{false, true});
            case 2 -> Value.of(new boolean[]{true, true});
            default -> throw new IllegalStateException("Number "+ number + " is invalid for 3-digit logic");
        };
    }

    public static Integer toTripletLogicNumber(Value value){
        if (value.equals(Value.of(new boolean[]{false, false}))){
            return 0;
        } else if (value.equals(Value.of(new boolean[]{false, true}))){
            return 1;
        } else if (value.equals(Value.of(new boolean[]{true, true}))){
            return 2;
        } else{
            throw new IllegalStateException("Value "+ value + " is invalid for 3-digit logic");
        }
    }

    public static List<Value> toTripletLogicBits(List<Integer> numbers){
        return numbers.stream().map(BitConverterUtils::toTripletLogicBits).toList();
    }

    public static Value toFourDigitLogicBits(int number){
        return switch (number){
            case 0 -> Value.of(new boolean[]{false, false});
            case 1 -> Value.of(new boolean[]{false, true});
            case 2 -> Value.of(new boolean[]{true, false});
            case 3 -> Value.of(new boolean[]{true, true});
            default -> throw new IllegalStateException("Number "+ number + " is invalid for 4-digit logic");
        };
    }


    public static Integer toFourDigitLogicNumber(Value value){
        if (value.equals(Value.of(new boolean[]{false, false}))){
            return 0;
        } else if (value.equals(Value.of(new boolean[]{false, true}))){
            return 1;
        } else if (value.equals(Value.of(new boolean[]{true, false}))){
            return 2;
        } else if (value.equals(Value.of(new boolean[]{true, true}))){
            return 3;
        } else{
            throw new IllegalStateException("Value "+ value + " is invalid for 4-digit logic");
        }
    }

    public static List<Value> toFourDigitLogicBits(List<Integer> numbers){
        return numbers.stream().map(BitConverterUtils::toFourDigitLogicBits).toList();
    }
}
