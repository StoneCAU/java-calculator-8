package calculator;

import java.math.BigInteger;
import java.util.List;

public class Calculator {
    private final Parser parser = new Parser();
    private final Validator validator = new Validator();

    public BigInteger calculate(String input) {
        // 공백("") 입력시 0을 리턴
        if (input.isEmpty()) return BigInteger.ZERO;

        // 1. 파싱 단계
        boolean isCustom = parser.isCustomDelimiter(input);
        String delimiter = parser.extractDelimiter(input);
        String numbers = parser.extractNumbers(input);

        // 2. 검증 단계
        if (isCustom) validator.validateCustomDelimiterRules(delimiter, numbers);
        else validator.validateDefaultDelimiterRules(numbers);

        List<String> tokens = parser.split(delimiter, numbers);
        validator.validateTokens(tokens);

        // 3. 계산 단계
        return sum(tokens);
    }

    private BigInteger sum(List<String> tokens) {
        BigInteger result = BigInteger.ZERO;

        for (String token : tokens) {
            result = result.add(new BigInteger(token));
        }

        return result;
    }
}
