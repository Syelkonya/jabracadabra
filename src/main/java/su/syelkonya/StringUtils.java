package su.syelkonya;

import java.util.LinkedHashMap;

public class StringUtils {

    public static String reverse(String line) {
        char[] lineArray = line.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = lineArray.length - 1; i >= 0; i--) {
            stringBuilder.append(lineArray[i]);
        }
        return stringBuilder.toString();
    }

    /**
     * Метод isPalindrome(String s) - палиндром без учета регистра и пробелов.
     * "А роза упала на лапу Азора" дает true.
     */
    public static boolean isPalindrome(String line) {
        if (line == null) return false;
        String lineAfterFormatting = line.toLowerCase().replace(" ", "");
        String reverseLineAfterFormatting = reverse(lineAfterFormatting);
        return lineAfterFormatting.equals(reverseLineAfterFormatting);
    }


    /**
     * Метод countChars(String s) -
     * countChars("hello") дает {h=1, e=1, l=2, o=1}.
     */
    public static String countChars(String line){
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (char ch: line.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        return map.toString();
    }

}
