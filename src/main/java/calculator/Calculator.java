package calculator;

import java.math.BigInteger;
import java.util.List;

public class Calculator {
    private final Parser parser = new Parser();

    public BigInteger calculate(String input) {
        // 1. 빈 문자열 체크
        if (input.isEmpty()) {
            return BigInteger.ZERO;
        }

        // 2. 파싱
        List<String> numbers = parser.parse(input);

        // 3. 검증

        // 4. 계산
        return sum(numbers);
    }

    private BigInteger sum(List<String> numbers) {
        BigInteger result = BigInteger.ZERO;
        for (String number : numbers) {
            result = result.add(new BigInteger(number));
        }
        return result;
    }
}
