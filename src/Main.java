import ordinals.NormalFormat;
import ordinals.Normalizer;
import parser.ExpressionParser;
import operations.Expression;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new FileInputStream("input.txt"));
            List<String> answer = getAnswer(sc);

            try {
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("output.txt"),
                        StandardCharsets.UTF_8
                ))) {
                    for (String ans : answer) {
                        writer.write(ans + LINE_SEPARATOR);
                    }
                }
            } catch (IOException e) {
                System.err.println("Some bad with output");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    private static List<String> getAnswer(Scanner sc) {
        List<String> result = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] arr = sc.nextLine().trim().split("=");
            ExpressionParser parser = new ExpressionParser();
            Expression leftExpression = parser.parse(arr[0]);
            Expression rightExpression = parser.parse(arr[1]);

            NormalFormat left = new Normalizer().normalize(leftExpression);
            NormalFormat right = new Normalizer().normalize(rightExpression);
            String answer = "Не равны";
            if (Objects.equals(left, right)) {
//                System.out.println("equal");
                answer = "Равны";
            } else {
                System.out.println(arr[0] + "=" + arr[1]);
                System.out.println(left);
                System.out.println(right);
//                System.out.println("not equal");
            }
            result.add(answer);
        }
        return result;
    }
}
