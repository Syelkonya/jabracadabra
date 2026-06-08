package su.syelkonya;

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

}
