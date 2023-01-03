package org.lois.logic.logics;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;
import org.lois.logic.domain.Value;

@SuppressWarnings("unused")
public class ClassicLogicTest extends BaseLogicTest {
    @BeforeEach
    public void setup() {
        logic = new ClassicLogic();
    }

    private static Stream<Arguments> notProvider() {
        return Stream.of(
                Arguments.of(value(true), value(false)),
                Arguments.of(value(false), value(true))
        );
    }

    private static Stream<Arguments> andProvider() {
        return Stream.of(
                Arguments.of(value(false), value(false), value(false)),
                Arguments.of(value(false), value(true), value(false)),
                Arguments.of(value(true), value(false), value(false)),
                Arguments.of(value(true), value(true), value(true))
        );
    }

    protected static Stream<Arguments> orProvider() {
        return Stream.of(
                Arguments.of(value(false), value(false), value(false)),
                Arguments.of(value(false), value(true), value(true)),
                Arguments.of(value(true), value(false), value(true)),
                Arguments.of(value(true), value(true), value(true))
        );
    }

    private static Stream<Arguments> equalProvider() {
        return Stream.of(
                Arguments.of(value(false), value(false), value(true)),
                Arguments.of(value(false), value(true), value(false)),
                Arguments.of(value(true), value(false), value(false)),
                Arguments.of(value(true), value(true), value(true))
        );
    }

    private static Stream<Arguments> implicationProvider() {
        return Stream.of(
                Arguments.of(value(false), value(false), value(true)),
                Arguments.of(value(false), value(true), value(true)),
                Arguments.of(value(true), value(false), value(false)),
                Arguments.of(value(true), value(true), value(true))
        );
    }

    @Override
    public void diamond(Value value, Value expected) {
    }

    @Override
    public void square(Value value, Value expected) {
    }
}
