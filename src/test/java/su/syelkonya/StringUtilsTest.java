package su.syelkonya;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "hello,        olleh",
            "a,            a",
            "madam,        madam",
            "hello world,  dlrow olleh",
            "'',           ''",
            "123,          321",
            "#@!,          !@#"
    })
    void reverse(String input, String expected) {
        assertEquals(expected, StringUtils.reverse(input));
    }

    @ParameterizedTest
    @NullSource
    void reverseNullThrowsException(String input) {
        assertThrows(NullPointerException.class, () -> StringUtils.reverse(input));
    }

    @ParameterizedTest
    @CsvSource({
            "madam,                         true",
            "racecar,                       true",
            "hello,                         false",
            "А роза упала на лапу Азора,    true",
            "а,                             true",
            "ab,                            false",
    })
    void isPalindrome(String input, boolean expected) {
        assertEquals(expected, StringUtils.isPalindrome(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"   ", ""})
    void isPalindromeBlankAndEmpty(String input) {
        assertTrue(StringUtils.isPalindrome(input));
    }

    @ParameterizedTest
    @NullSource
    void isPalindromeNullReturnsFalse(String input) {
        assertFalse(StringUtils.isPalindrome(input));
    }

    static Stream<Arguments> provideCountChars() {
        return Stream.of(
                Arguments.of("hello", "{h=1, e=1, l=2, o=1}"),
                Arguments.of("aaa", "{a=3}"),
                Arguments.of("ab", "{a=1, b=1}"),
                Arguments.of("bda", "{b=1, d=1, a=1}"),
                Arguments.of("a", "{a=1}"),
                Arguments.of("", "{}")
        );
    }

    @ParameterizedTest
    @MethodSource("provideCountChars")
    void countChars(String input, String expected) {
        assertEquals(expected, StringUtils.countChars(input));
    }

    @ParameterizedTest
    @NullSource
    void countCharsNullThrowsException(String input) {
        assertThrows(NullPointerException.class, () -> StringUtils.countChars(input));
    }

}