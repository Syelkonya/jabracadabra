package su.syelkonya;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamUtils {

    public int returnEvenNumbersCount(List<Integer> integerList){
        return integerList.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(n -> n)
                .sum();
    }

    public List<String> allToUpperCase(List<String> names){
        return names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(ArrayList::new));
    }

//  Реализовать метод, который возвращает кол-во строк в списке длиннее n символов.
    public int countLinesLongerThan(List<String> lines, int n){
        return (int) lines.stream()
                .filter(l -> l.length() > n)
                .count();
    }

}
