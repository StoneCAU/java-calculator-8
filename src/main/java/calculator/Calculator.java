package calculator;

import java.math.BigInteger;
import java.util.List;

public class Calculator {
    private final Parser parser;
    private final Validator validator;
    
    public Calculator(Parser parser, Validator validator) {
        this.parser = parser;
        this.validator = validator;
    }

    public BigInteger calculate(String input) {
        // 공백("") 입력시 0을 리턴
        if (input.isEmpty()) return BigInteger.ZERO;

        List<String> tokens = parseAndValidate(input);
        return sum(tokens);
    }

    private List<String> parseAndValidate(String input) {
        // 파싱
        boolean isCustom = parser.isCustomDelimiter(input);
        String delimiter = parser.extractDelimiter(input);
        String numbers = parser.extractNumbers(input);

        // 검증
        if (isCustom) validator.validateCustomDelimiterRules(delimiter, numbers);
        else validator.validateDefaultDelimiterRules(numbers);

        List<String> tokens = parser.split(delimiter, numbers);
        validator.validateTokens(tokens);

        return tokens;
    }

    private BigInteger sum(List<String> tokens) {
        BigInteger result = BigInteger.ZERO;

        for (String token : tokens) {
            result = result.add(new BigInteger(token));
        }

        return result;
    }
}
