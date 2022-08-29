package input.output.ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringJoiner;

public class Exercise1 {
    public static final String FILE_PATH = "src/main/java/input/output/ex1/file.txt";
    public static final String VALID_PHONE_NUMBERS = "^(\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4})$";

    private Exercise1() {
    }

    public static void printValidPhoneNumbers() {
        try (Scanner sc = new Scanner(new File(FILE_PATH))) {
            var output = new StringJoiner("\n");
            while (sc.hasNextLine()) {
                var curr = sc.nextLine();
                if (curr.matches(VALID_PHONE_NUMBERS)) output.add(curr);
            }
            System.out.println(output);
        } catch (FileNotFoundException e) {
            System.out.println("File \"file.txt\" is absent in the current directory");
        }
    }

}
