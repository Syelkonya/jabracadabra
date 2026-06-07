package su.syelkonya;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

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
}