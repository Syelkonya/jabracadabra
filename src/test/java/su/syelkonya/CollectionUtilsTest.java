package su.syelkonya;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CollectionUtilsTest {

    static Stream<Arguments> provideRemoveDuplicates() {
        return Stream.of(
                Arguments.of(List.of(3, 1, 3, 2, 1),    List.of(3, 1, 2)),
                Arguments.of(List.of(1, 2, 3),           List.of(1, 2, 3)),
                Arguments.of(List.of(1, 1, 1),           List.of(1)),
                Arguments.of(List.of(1),                 List.of(1)),
                Arguments.of(List.of(),                  List.of())
        );
    }

    @ParameterizedTest
    @MethodSource("provideRemoveDuplicates")
    void removeDuplicates(List<Integer> input, List<Integer> expected) {
        assertEquals(expected, CollectionUtils.removeDuplicates(input));
    }

    @ParameterizedTest
    @NullSource
    void removeDuplicatesNullThrowsException(List<Integer> input) {
        assertThrows(NullPointerException.class, () -> CollectionUtils.removeDuplicates(input));
    }
}
