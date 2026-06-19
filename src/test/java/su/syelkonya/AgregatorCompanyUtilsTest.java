package su.syelkonya;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import su.syelkonya.company.agregator.AgregatorCompanyUtils;
import su.syelkonya.company.agregator.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AgregatorCompanyUtilsTest {

    static Stream<Arguments> provideGetOneLineNames() {
        return Stream.of(
                Arguments.of(List.of(new Employee("Павел"), new Employee("Снежанна")),
                        "Павел, Снежанна"
                ),
                Arguments.of(List.of(), "")
        );
    }

    @ParameterizedTest
    @MethodSource("provideGetOneLineNames")
    void getOneLineNamesTest(List<Employee> employees, String expectedNames) {
        AgregatorCompanyUtils agregatorCompanyUtils = new AgregatorCompanyUtils();
        assertEquals(expectedNames, agregatorCompanyUtils.getOneLineNames(employees));
    }

    static Stream<Arguments> provideGetFirstFromXCompany() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Employee("Павел", 25, new BigDecimal(30000), "ЖКХ"),
                                new Employee("Павел", 25, new BigDecimal(30000), "ВК"),
                                new Employee("Снежанна", 25, new BigDecimal(30000), "Yandex"),
                                new Employee("Technique", 25, new BigDecimal(50000), "ВК")
                        ),
                        "ВК",
                        new Employee("Павел", 25, new BigDecimal(30000), "ВК")
                ),
                Arguments.of(
                        List.of(
                                new Employee("Павел ПАВ", 25, new BigDecimal(30000), "ЖКХ"),
                                new Employee("Павел", 25, new BigDecimal(30000), "ВК"),
                                new Employee("Снежанна", 25, new BigDecimal(30000), "Yandex"),
                                new Employee("Technique", 25, new BigDecimal(50000), "ВК")
                        ),
                        "Yandex",
                        new Employee("Снежанна", 25, new BigDecimal(30000), "Yandex")
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideGetFirstFromXCompany")
    void getFirstFromXCompanyTest(List<Employee> employees, String companyName, Employee expectedEmployee) {
        AgregatorCompanyUtils agregatorCompanyUtils = new AgregatorCompanyUtils();
        assertEquals(expectedEmployee, agregatorCompanyUtils.getFirstFromXCompany(employees, companyName));
    }

    static Stream<Arguments> provideGetFirstFromXCompanyNotFound() {
        return Stream.of(
                Arguments.of(List.of(),
                        "Yandex"),
                Arguments.of(
                        List.of(new Employee("Павел", 25, new BigDecimal(30000), "ВК")),
                        "НесуществующаяКомпания"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideGetFirstFromXCompanyNotFound")
    void getFirstFromXCompanyNotFound_throwsException(List<Employee> employees, String companyName) {
        AgregatorCompanyUtils agregatorCompanyUtils = new AgregatorCompanyUtils();
        assertThrows(
                NoSuchElementException.class,
                () -> agregatorCompanyUtils.getFirstFromXCompany(employees, companyName)
        );
    }
}
