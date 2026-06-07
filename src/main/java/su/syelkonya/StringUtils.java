package su.syelkonya;

public class StringUtils {

    public static String reverse(String line){
        char[] lineArray = line.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = lineArray.length-1; i >=0 ; i--) {
            stringBuilder.append(lineArray[i]);
        }
        return stringBuilder.toString();
    }

}
