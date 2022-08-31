package input.output.ex3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Exercise3 {

    public static String FILE_PATH = "src/main/java/input/output/ex3/words.txt";

    private Exercise3() {
    }

    public static void printWordFrequency() {
        var read = new StringJoiner("\n");
        try (Scanner sc = new Scanner(new File(FILE_PATH))) {
            while (sc.hasNextLine())
                read.add(sc.nextLine());
        } catch (FileNotFoundException e) {
            System.out.printf("File \"%s\" is absent in the current directory", FILE_PATH);
        }

        var resultingMap = new HashMap<String, Integer>();
        Arrays.stream(read.toString().split("(\\n|\\s+)"))
                .forEach(word -> resultingMap.compute(word, (k, v) -> v == null ? 1 : ++v));

        System.out.println(
                resultingMap.entrySet()
                        .stream()
                        .sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue())
                        .map(entry -> String.format("%s %d", entry.getKey(), entry.getValue()))
                        .collect(Collectors.joining("\n"))
        );
    }
}
