package input.output.ex2;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class Exercise2 {
    public static String INPUT_FILE_PATH = "src/main/java/input/output/ex2/file.txt";
    public static String OUTPUT_FILE_PATH = "src/main/java/input/output/ex2/user.json";

    private Exercise2() {
    }

    public static void txtToJson() {
        var readData = new StringJoiner("\n");
        try (Scanner sc = new Scanner(new File(INPUT_FILE_PATH))) {
            while (sc.hasNextLine())
                readData.add(sc.nextLine());
        } catch (FileNotFoundException e) {
            System.out.printf("File \"%s\" is absent in the current directory", INPUT_FILE_PATH);
        }

        var temp = readData.toString().split("\n");
        var attributes = temp[0].split("\s");
        var values = IntStream.range(1, temp.length)
                .mapToObj(num -> temp[num].split("\s"))
                .toList();

        var jsonFormat = jsonWriter(values, attributes);

        writeToFile(jsonFormat);

        System.out.println(jsonFormat);
    }

    private static String jsonWriter(List<String[]> values, String... attributes) {
        return values.stream()
                .reduce(
                        new StringJoiner(",\n", "[\n", "\n]"),
                        (sJoiner, vals) -> sJoiner.add(singleObjectJsonWriter(vals, attributes).replaceAll("(?m)^", "\t")), (x, y) -> x
                )
                .toString();
    }

    private static String singleObjectJsonWriter(String[] values, String... attributes) {
        if (values.length != attributes.length)
            throw new IllegalArgumentException(String.format("The amount of attributes (%d) is not the same as the amount of values (%d)", attributes.length, values.length));

        var res = new StringJoiner(",\n", "{\n", "\n}");
        for (int i = 0; i < values.length; i++)
            res.add(String.format("\t\"%s\": \"%s\"", attributes[i], values[i]));

        return res.toString();
    }

    private static void writeToFile(String text) {
        try (var writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE_PATH)))) {
            writer.write(text);
        } catch (IOException e) {
            System.out.printf("Something went wrong when writing to file \"%s\"", OUTPUT_FILE_PATH);
        }
    }
}
