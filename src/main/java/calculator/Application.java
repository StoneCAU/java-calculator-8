package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.math.BigInteger;

public class Application {
    private static final String INPUT_MESSAGE = "덧셈할 문자열을 입력해 주세요.";
    private static final String OUTPUT_MESSAGE = "결과 : %s";

    public static void main(String[] args) {
        Calculator calculator = new Calculator(new Parser());

        System.out.println(INPUT_MESSAGE);
        String input = Console.readLine();

        BigInteger result = calculator.calculate(input);

        System.out.printf(OUTPUT_MESSAGE, result);
    }
}
