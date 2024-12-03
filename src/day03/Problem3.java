package day03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Problem3 {
    public static void main(String[] args) {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;
        boolean enabled=true;
        var line=br.lines().collect(Collectors.joining());
        String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";

        for (int i = 0; i < line.length() - 8; i++) {
            //this string represent mul operation or do()/don't()
            String mulExpression =line.substring(i, line.indexOf(')', i) + 1);
            if (enabled && line.charAt(i) == 'd' && mulExpression.equals("don't()")){
                enabled=false;
            }
            if (!enabled && line.charAt(i) == 'd' && mulExpression.equals("do()")){
                enabled=true;
            }

            if (enabled && line.charAt(i) == 'm' && mulExpression.matches(regex)) {
                Integer operand1 = Integer.parseInt(line.substring(i + 4, line.indexOf(',', i + 4)));
                Integer operand2 = Integer.parseInt(line.substring(line.indexOf(',', i + 4) + 1, line.indexOf(')', i + 4)));
                sum += operand1 * operand2;
            }
        }
        System.out.println(sum);
    }
}
