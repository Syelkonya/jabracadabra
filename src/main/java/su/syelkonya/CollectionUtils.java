package su.syelkonya;

import java.util.List;

public class CollectionUtils {

    /**
     * Метод removeDuplicates(List list) - убрать дубликаты, сохранив порядок первых вхождений.
     * [3, 1, 3, 2, 1] дает [3, 1, 2].
     */
    public static List<Integer> removeDuplicates(List<Integer> list){
        return list.stream().distinct().toList();
    }

    //Метод mergeSorted(List a, List b) - слить два отсортированных списка в один отсортированный.
    public static List<Integer> mergeSorted(List<Integer> a, List<Integer> b){
        return a;
    }

}
