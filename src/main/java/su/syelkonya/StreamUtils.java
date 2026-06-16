package su.syelkonya;

import java.util.List;

public class StreamUtils {

    public int returnOnlyEvenNumbers(List<Integer> integerList){
        return integerList.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(n -> n)
                .sum();
    }

}
