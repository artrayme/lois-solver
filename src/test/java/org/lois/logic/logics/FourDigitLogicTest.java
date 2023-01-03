package org.lois.logic.logics;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;

@SuppressWarnings("unused")
public class FourDigitLogicTest extends BaseLogicTest {
    @BeforeEach
    public void setup() {
        logic = new FourDigitLogic();
    }

    private static Stream<Arguments> notProvider() {
        return Stream.of(
                Arguments.of(value(false, false), value(true, true)),
                Arguments.of(value(false, true), value(true, false)),
                Arguments.of(value(true, false), value(false, true)),
                Arguments.of(value(true, true), value(false, false))
        );
    }

    private static Stream<Arguments> andProvider() {
        return Stream.of(
                Arguments.of(value(false, false), value(false, false), value(false, false)),
                Arguments.of(value(false, false), value(false, true), value(false, false)),
                Arguments.of(value(false, false), value(true, false), value(false, false)),
                Arguments.of(value(false, false), value(true, true), value(false, false)),
                Arguments.of(value(false, true), value(false, false), value(false, false)),
                Arguments.of(value(false, true), value(false, true), value(false, true)),
                Arguments.of(value(false, true), value(true, false), value(false, false)),
                Arguments.of(value(false, true), value(true, true), value(false, true)),
                Arguments.of(value(true, false), value(false, false), value(false, false)),
                Arguments.of(value(true, false), value(false, true), value(false, false)),
                Arguments.of(value(true, false), value(true, false), value(true, false)),
                Arguments.of(value(true, false), value(true, true), value(true, false)),
                Arguments.of(value(true, true), value(false, false), value(false, false)),
                Arguments.of(value(true, true), value(false, true), value(false, true)),
                Arguments.of(value(true, true), value(true, false), value(true, false)),
                Arguments.of(value(true, true), value(true, true), value(true, true))
        );
    }

    private static Stream<Arguments> orProvider() {
        return Stream.of(
                Arguments.of(value(false, false), value(false, false), value(false, false)),
                Arguments.of(value(false, false), value(false, true), value(false, true)),
                Arguments.of(value(false, false), value(true, false), value(true, false)),
                Arguments.of(value(false, false), value(true, true), value(true, true)),
                Arguments.of(value(false, true), value(false, false), value(false, true)),
                Arguments.of(value(false, true), value(false, true), value(false, true)),
                Arguments.of(value(false, true), value(true, false), value(true, true)),
                Arguments.of(value(false, true), value(true, true), value(true, true)),
                Arguments.of(value(true, false), value(false, false), value(true, false)),
                Arguments.of(value(true, false), value(false, true), value(true, true)),
                Arguments.of(value(true, false), value(true, false), value(true, false)),
                Arguments.of(value(true, false), value(true, true), value(true, true)),
                Arguments.of(value(true, true), value(false, false), value(true, true)),
                Arguments.of(value(true, true), value(false, true), value(true, true)),
                Arguments.of(value(true, true), value(true, false), value(true, true)),
                Arguments.of(value(true, true), value(true, true), value(true, true))
        );
    }

    private static Stream<Arguments> equalProvider() {
        return Stream.of(
                Arguments.of(value(false, false), value(false, false), value(true, true)),
                Arguments.of(value(false, false), value(false, true), value(false, false)),
                Arguments.of(value(false, false), value(true, false), value(false, false)),
                Arguments.of(value(false, false), value(true, true), value(false, false)),
                Arguments.of(value(false, true), value(false, false), value(false, false)),
                Arguments.of(value(false, true), value(false, true), value(true, true)),
                Arguments.of(value(false, true), value(true, false), value(false, false)),
                Arguments.of(value(false, true), value(true, true), value(false, false)),
                Arguments.of(value(true, false), value(false, false), value(false, false)),
                Arguments.of(value(true, false), value(false, true), value(false, false)),
                Arguments.of(value(true, false), value(true, false), value(true, true)),
                Arguments.of(value(true, false), value(true, true), value(false, false)),
                Arguments.of(value(true, true), value(false, false), value(false, false)),
                Arguments.of(value(true, true), value(false, true), value(false, false)),
                Arguments.of(value(true, true), value(true, false), value(false, false)),
                Arguments.of(value(true, true), value(true, true), value(true, true))
        );
    }

    private static Stream<Arguments> implicationProvider() {
        return Stream.of(
                Arguments.of(value(false, false), value(false, false), value(true, true)),
                Arguments.of(value(false, false), value(false, true), value(true, true)),
                Arguments.of(value(false, false), value(true, false), value(true, true)),
                Arguments.of(value(false, false), value(true, true), value(true, true)),
                Arguments.of(value(false, true), value(false, false), value(true, false)),
                Arguments.of(value(false, true), value(false, true), value(true, true)),
                Arguments.of(value(false, true), value(true, false), value(true, false)),
                Arguments.of(value(false, true), value(true, true), value(true, true)),
                Arguments.of(value(true, false), value(false, false), value(false, true)),
                Arguments.of(value(true, false), value(false, true), value(false, true)),
                Arguments.of(value(true, false), value(true, false), value(true, true)),
                Arguments.of(value(true, false), value(true, true), value(true, true)),
                Arguments.of(value(true, true), value(false, false), value(false, false)),
                Arguments.of(value(true, true), value(false, true), value(false, true)),
                Arguments.of(value(true, true), value(true, false), value(true, false)),
                Arguments.of(value(true, true), value(true, true), value(true, true))
        );
    }

    private static Stream<Arguments> diamondProvider() {
        return Stream.of(
                Arguments.of(value(false, false), value(false, false)),
                Arguments.of(value(false, true), value(true, true)),
                Arguments.of(value(true, false), value(true, true)),
                Arguments.of(value(true, true), value(true, true))
        );
    }

    private static Stream<Arguments> squareProvider() {
        return Stream.of(
                Arguments.of(value(false, false), value(false, false)),
                Arguments.of(value(false, true), value(false, false)),
                Arguments.of(value(true, false), value(false, false)),
                Arguments.of(value(true, true), value(true, true))
        );
    }
}
