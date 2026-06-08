package su.syelkonya;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

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

}