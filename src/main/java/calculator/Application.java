package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.math.BigInteger;

public class Application {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        // 1. 입력
        System.out.println(Constants.INPUT_MESSAGE);
        String input = Console.readLine();

        // 2. 계산
        BigInteger result = calculator.calculate(input);

        // 3. 출력
        System.out.printf(Constants.OUTPUT_MESSAGE, result);
    }
}
