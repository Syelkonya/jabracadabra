package su.syelkonya;


import java.util.*;
import java.util.stream.Collectors;

//Реализовать журнал оценок: класс GradeBook с методами addGrade(student, grade),
// average(student), bestStudent() (студент с максимальным средним).
public class GradeBook {

    HashMap<String, List<Double>> gradeBookMap = new HashMap<>();

    public void addGrade(String student, double grade) {
        List<Double> grades = gradeBookMap.getOrDefault(student, new ArrayList<>());
        grades.add(grade);
        gradeBookMap.put(student, grades);
    }

    public double average(String student) {
        List<Double> marks = gradeBookMap.get(student);
        double markSum = 0;
        for (double mark : marks) {
            markSum += mark;
        }
        return markSum / marks.size();
    }

    public String bestStudent() {
        Map<String, Double> nameAndMarkMap =
                 gradeBookMap.entrySet().stream()
                        .collect(Collectors.toMap(
                                entry -> entry.getKey(),
                                entry -> average(entry.getKey())
                        ));
        return Collections.max(nameAndMarkMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

}
