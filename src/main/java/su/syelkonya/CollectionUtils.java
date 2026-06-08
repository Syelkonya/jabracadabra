package su.syelkonya;

import java.util.*;

public class CollectionUtils {

    /**
     * Метод removeDuplicates(List list) - убрать дубликаты, сохранив порядок первых вхождений.
     * [3, 1, 3, 2, 1] дает [3, 1, 2].
     */
    public static List<Integer> removeDuplicates(List<Integer> list) {
        return list.stream().distinct().toList();
    }

    /**
     * Метод mergeSorted(List a, List b) - слить два отсортированных списка в один отсортированный.
     */
    public static List<Integer> mergeSorted(List<Integer> a, List<Integer> b) {
        List<Integer> resultList = new ArrayList<>();
        for (int k = 0, l = 0; k < a.size() || l < b.size(); ) {
            if (k == a.size()) {
                resultList.add(b.get(l++));
                continue;
            }
            if (l == b.size()) {
                resultList.add(a.get(k++));
                continue;
            }

            if (a.get(k) < b.get(l)) {
                resultList.add(a.get(k++));
            } else if (a.get(k) > b.get(l)) {
                resultList.add(b.get(l++));
            } else {
                resultList.add(a.get(k++));
                resultList.add(b.get(l++));
            }
        }
        return resultList;
    }

    /**
     * Метод wordFrequency(String text) - вывести топ-3 самых частых слова в строке.
     */
    public static List<String> wordFrequency(String text) {
        List<String> textList = List.of(text.split(" "));
        HashMap<String, Integer> wordFrequency = new HashMap<>();
        for (String word : textList) {
            int currentFrequency = wordFrequency.getOrDefault(word, 0) + 1;
            wordFrequency.put(word, currentFrequency);
        }
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(wordFrequency.entrySet());

        entries.sort(
                (a, b) ->
                        b.getValue() - a.getValue()
        );

        List<String> top3 = new ArrayList<>();
        for (int i = 0; i < Math.min(3, entries.size()); i++) {
            top3.add(entries.get(i).getKey());
        }
        return top3;
    }

}
