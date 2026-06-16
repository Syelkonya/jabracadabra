package su.syelkonya.company.agregator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Реализовать метод, который принимает список сотрудников и отдает среднюю зп по каждой фирме (вернуть Map<String, BigDecimal>)
 */
public class AgregatorCompanyUtils {

    public Map<String, BigDecimal> getMediumSalaryOnEachCompany(List<Employee> employeeList) {
        return employeeList.stream()
                .collect(Collectors.groupingBy(
                                Employee::getCompany,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        employees -> {
                                            BigDecimal allSalary = employees.stream()
                                                    .map(Employee::getSalary)
                                                    .reduce(BigDecimal.ZERO, BigDecimal::add);
                                            BigDecimal employeesSize = BigDecimal.valueOf(employees.size());
                                            return allSalary.divide(employeesSize, 2, RoundingMode.HALF_UP);
                                        }
                                )
                        )
                );
    }

//     Используем класс сотрудника, метод принимает список сотрудников, отдает имена всех сотрудников одной строкой через запятую.
    public String getOneLineNames(List<Employee> employees){
        return employees
                .stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", "));
    }

//     Используем класс сотрудника, метод принимает список сотрудников, вернуть true если есть хоть один старше 50 лет.
    public boolean isAnyOlderThan50(List<Employee> employees){
        return employees
                .stream()
                .map(Employee::getAge)
                .anyMatch(a -> a > 50);
    }


}
