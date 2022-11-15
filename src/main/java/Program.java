
import java.util.Scanner;

public class Program {

    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter key word:");
        String keys = scanner.next();
        System.out.println("Enter text:");
        String text = scanner.next();
        int repeat_counter = Solution.solution(text.toUpperCase(), keys.toUpperCase().toCharArray());
        System.out.println("Number of matched " + repeat_counter);
    }
}
