package su.syelkonya;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StreamUtilsTest {

    static Stream<Arguments> provideEven() {
        return Stream.of(
                Arguments.of(List.of(3, 1, 3, 2, 1), 2),
                Arguments.of(List.of(3, 4, 3, 2, 1), 6),
                Arguments.of(List.of(1, 2, 3), 2),
                Arguments.of(List.of(1, 1, 1), 0),
                Arguments.of(List.of(1), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideEven")
    void removeDuplicates(List<Integer> input, int expected) {
        assertEquals(expected, new StreamUtils().returnOnlyEvenNumbers(input));
    }
}
