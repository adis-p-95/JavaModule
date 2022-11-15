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

    public static int solution(String input, char[] keys) throws SolutionException
    {
      return solution(input, keys, 200000);
    }

    public static int solution(String input) throws SolutionException
    {
        return solution(input, "BALLOON".toCharArray());
    }

    public static int solution(String input, char[] keys, int input_max_length) throws SolutionException {

        if (input.length() > input_max_length) {
            throw new SolutionException("Input is larger than " + input_max_length + ".");
        }

        if (input.matches(".*\\d.*")) { //To check if input contains any numbers.
            throw new SolutionException("Input text contains numbers.");
        }

        if (!input.matches("[A-Z]+")) { //To check if input is not only [A-Z].
            throw new SolutionException("Input text is contains lower case letters.");
        }

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