package su.syelkonya;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamUtils {

    public int returnEvenNumbersCount(List<Integer> integerList) {
        return integerList.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(n -> n)
                .sum();
    }

    public List<String> allToUpperCase(List<String> names) {
        return names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    //  Реализовать метод, который возвращает кол-во строк в списке длиннее n символов.
    public int countLinesLongerThan(List<String> lines, int n) {
        return (int) lines.stream()
                .filter(l -> l.length() > n)
                .count();
    }

    //    Реализовать метод, который возвращает set первых букв каждого слова из списка.
    public Set<Character> firstLetterSet(List<String> words) {
        return words.stream()
                .filter(w -> !w.isEmpty())
                .map(w -> w.charAt(0))
                .collect(Collectors.toSet());
    }

//   Реализовать метод, который принимает список Integer b после сортировки по убыванию возвращает 4 по порядку число.
    public int getFourthAfterDescSort(List<Integer> b){
        return b.stream()
                .sorted((a1, a2) -> (a2 - a1))
                .toList()
                .get(3);
    }

//    10) Дан List<List<Integer>> - собрать 1 список всех чисел.
    public List<Integer> getListFromListOfLists(List<List<Integer>> listList){
        return listList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
