import parser.ExpressionParser;
import operations.Expression;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new FileInputStream("input.txt"));
            String[] arr = sc.nextLine().trim().split("=");

            ExpressionParser parser = new ExpressionParser();
            Expression leftExpression = parser.parse(arr[0]);
            Expression rightExpression = parser.parse(arr[1]);
            System.out.println(leftExpression.toString());
            System.out.println(rightExpression.toString());

            NormalFormat left = new Normalizer().normalize(leftExpression);
            NormalFormat right = new Normalizer().normalize(rightExpression);
            String answer = "Не равны";
            if (left == right) {
                answer = "Равны";
            }

            try {
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("output.txt"),
                        StandardCharsets.UTF_8
                ))) {
                    writer.write(answer);
                }
            } catch (IOException e) {
                System.err.println("Some bad with output");
            }
        } catch (Exception e) {
            System.err.println("File not found");
        }
    }
}
