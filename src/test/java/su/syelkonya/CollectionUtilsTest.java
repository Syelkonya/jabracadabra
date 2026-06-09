package su.syelkonya;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CollectionUtilsTest {

    static Stream<Arguments> provideRemoveDuplicates() {
        return Stream.of(
                Arguments.of(List.of(3, 1, 3, 2, 1), List.of(3, 1, 2)),
                Arguments.of(List.of(1, 2, 3), List.of(1, 2, 3)),
                Arguments.of(List.of(1, 1, 1), List.of(1)),
                Arguments.of(List.of(1), List.of(1)),
                Arguments.of(List.of(), List.of())
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

    static Stream<Arguments> provideMergeSorted() {
        return Stream.of(
                Arguments.of(List.of(1, 3, 5), List.of(2, 4, 6), List.of(1, 2, 3, 4, 5, 6)),
                Arguments.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(1, 2, 3, 4, 5, 6)),
                Arguments.of(List.of(4, 5, 6), List.of(1, 2, 3), List.of(1, 2, 3, 4, 5, 6)),
                Arguments.of(List.of(1, 1, 2), List.of(1, 2, 3), List.of(1, 1, 1, 2, 2, 3)),
                Arguments.of(List.of(1, 3, 3, 5), List.of(2, 3, 4, 6), List.of(1, 2, 3, 3, 3, 4, 5, 6)),
                Arguments.of(List.of(1), List.of(2), List.of(1, 2)),
                Arguments.of(List.of(), List.of(), List.of())
        );
    }

    @ParameterizedTest
    @MethodSource("provideMergeSorted")
    void mergeSorted(List<Integer> a, List<Integer> b, List<Integer> expected) {
        assertEquals(expected, CollectionUtils.mergeSorted(a, b));
    }

    static Stream<Arguments> provideWordFrequency() {
        return Stream.of(
                Arguments.of(
                        "a a a b b c",
                        List.of("a", "b", "c")
                ),
                Arguments.of(
                        "dog dog dog cat cat bird",
                        List.of("dog", "cat", "bird")
                ),
                Arguments.of(
                        "hello",
                        List.of("hello")
                ),
                Arguments.of(
                        "a a a a a a",
                        List.of("a")
                ),
                Arguments.of(
                        "apple apple apple banana banana cherry cherry mango mango mango mango kiwi kiwi kiwi kiwi kiwi",
                        List.of("kiwi", "mango", "apple")
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideWordFrequency")
    void wordFrequency(String input, List<String> expected) {
        assertEquals(expected, new ArrayList<>(CollectionUtils.wordFrequency(input)));
    }

    @ParameterizedTest
    @NullSource
    void wordFrequencyNullThrowsException(String input) {
        assertThrows(NullPointerException.class, () -> CollectionUtils.wordFrequency(input));
    }


    static Stream<Arguments> provideQuickSort() {
        return Stream.of(
                Arguments.of(List.of(5, 3, 1, 4, 2),        List.of(1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, 4, 5),        List.of(1, 2, 3, 4, 5)),
                Arguments.of(List.of(5, 4, 3, 2, 1),        List.of(1, 2, 3, 4, 5)),
                Arguments.of(List.of(1),                     List.of(1)),
                Arguments.of(List.of(),                      List.of()),
                Arguments.of(List.of(3, 3, 3),               List.of(3, 3, 3)),
                Arguments.of(List.of(3, 1, 3, 2, 1),        List.of(1, 1, 2, 3, 3)),
                Arguments.of(List.of(-3, -1, -5, 0, 2),     List.of(-5, -3, -1, 0, 2))
        );
    }

    @ParameterizedTest
    @MethodSource("provideQuickSort")
    void quickSort(List<Integer> input, List<Integer> expected) {
        assertEquals(expected, CollectionUtils.quickSort(input));
    }

    @ParameterizedTest
    @NullSource
    void quickSortNullThrowsException(List<Integer> input) {
        assertThrows(NullPointerException.class, () -> CollectionUtils.quickSort(input));
    }

    static Stream<Arguments> provideMaskPhone() {
        return Stream.of(
                Arguments.of("+7999123456",     "+7999***3456"),
                Arguments.of("+79991234567",    "+7999***4567"),
                Arguments.of("123456789",       "12345***6789"),
                Arguments.of("12345678",        "12345678"),
                Arguments.of("1",              "1"),
                Arguments.of("",               "")
        );
    }

    @ParameterizedTest
    @MethodSource("provideMaskPhone")
    void maskPhone(String input, String expected) {
        assertEquals(expected, CollectionUtils.maskPhone(input));
    }

}
