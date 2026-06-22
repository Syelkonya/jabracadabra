package su.syelkonya.company.agregator;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 6) Создать класс сотрудника с именем, возрастом, зп и фирмой(строка).
 * Реализовать метод, который принимает список сотрудников и отдает среднюю зп по каждой фирме (вернуть Map<String, BigDecimal>)
 */
@Data
@AllArgsConstructor
public class Employee {

    private String name;
    private int age;
    private BigDecimal salary;
    private String company;

    public Employee(String name) {
        this.name = name;
    }
}
