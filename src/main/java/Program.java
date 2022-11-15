import java.io.*;
import java.util.Scanner;

public class Program {

    public static void main(String args[]) {

        try {

            File keys_file = new File(Configuration.KEYS_PATH);
            Scanner inputKeys = new Scanner(keys_file);
            String dataKeys = inputKeys.nextLine();

            File text_file = new File(Configuration.TEXT_PATH);
            Scanner inputText = new Scanner(text_file);

            FileWriter outputText = new FileWriter(Configuration.OUTPUT_PATH, false);

            int total_counter = 0;
            while (inputText.hasNextLine()) {
                String dataText = inputText.nextLine();
                try {
                    int repeat_counter = Solution.solution(dataText, dataKeys.toCharArray());
                    total_counter = total_counter + repeat_counter;
                    outputText.append(repeat_counter + " matches in line " + dataText + ".\r\n");
                } catch (SolutionException exception) {
                    outputText.append("Get error " + exception.getMessage() + " when process line " + dataText + ".\r\n");
                }
            }
            outputText.append("Total matches: " + total_counter);
            outputText.close();
        }
        catch (FileNotFoundException exception) {
            System.out.println("File related error: " + exception.getMessage());
        }
        catch (IllegalStateException exception) {
            System.out.println("Scanner related error:" + exception.getMessage());
        }
        catch (IOException exception) {
            System.out.println("Unable to read/write to file. Error: " + exception.getMessage());
        }
    }
}