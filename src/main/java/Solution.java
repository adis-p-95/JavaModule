public class Solution {

    private static String removeAllChars(String s, char[] chars){
        String s_short = s;
        for(int i = 0; i < chars.length; i++) {
            int j = s_short.indexOf(chars[i]);
            if ( j > -1) {
                // Remove character from string
                s_short = s_short.substring(0, j) + s_short.substring(j + 1);
            }
        }

        return s_short;
    }

    public static int solution(String input, char[] keys){
        int counter = 0;
        while(true){
            String output = removeAllChars(input, keys);
            if (output.length() == input.length() - keys.length) {
                input = output;
                counter++;
                System.out.println("Match #" + counter + ", the rest of string " +  input);
            }
            else {
                break;
            }
        }

        return counter;
    }
}
