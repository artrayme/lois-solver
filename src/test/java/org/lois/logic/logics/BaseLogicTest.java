package org.lois.logic.logics;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.lois.logic.domain.Logic;
import org.lois.logic.domain.Value;

public abstract class BaseLogicTest {
    protected Logic logic;

    public static Value value(boolean... booleans) {
        return Value.of(booleans);
    }

    @ParameterizedTest
    @MethodSource("notProvider")
    public void not(Value argument, Value expected) {
        var result = logic.not(argument);

        Assertions.assertThat(result).isEqualTo(
                expected
        );
    }

    @ParameterizedTest
    @MethodSource("andProvider")
    public void and(Value left, Value right, Value expected) {
        var result = logic.and(left, right);

        Assertions.assertThat(result).isEqualTo(
                expected
        );
    }

    @ParameterizedTest
    @MethodSource("orProvider")
    public void or(Value left, Value right, Value expected) {
        var result = logic.or(left, right);

        Assertions.assertThat(result).isEqualTo(
                expected
        );
    }

    @ParameterizedTest
    @MethodSource("equalProvider")
    public void equal(Value left, Value right, Value expected) {
        var result = logic.equal(left, right);

        Assertions.assertThat(result).isEqualTo(
                expected
        );
    }

    @ParameterizedTest
    @MethodSource("implicationProvider")
    public void implication(Value left, Value right, Value expected) {
        var result = logic.implication(left, right);

        Assertions.assertThat(result).isEqualTo(
                expected
        );
    }

    @ParameterizedTest
    @MethodSource("diamondProvider")
    public void diamond(Value value, Value expected) {
        var result = logic.diamond(value);

        Assertions.assertThat(result).isEqualTo(
                expected
        );
    }

    @ParameterizedTest
    @MethodSource("squareProvider")
    public void square(Value value, Value expected) {
        var result = logic.square(value);

        Assertions.assertThat(result).isEqualTo(
                expected
        );
    }
}
