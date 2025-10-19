package calculator;

import java.math.BigInteger;
import java.util.List;

public class Calculator {
    private final Parser parser;

    public Calculator(Parser parser) {
        this.parser = parser;
    }

    public BigInteger calculate(String input) {
        // 공백("") 입력시 0을 리턴
        if (input.isEmpty()) return BigInteger.ZERO;

        List<BigInteger> tokens = parser.parse(input);
        return sum(tokens);
    }

    private BigInteger sum(List<BigInteger> tokens) {
        BigInteger result = BigInteger.ZERO;

        for (BigInteger token : tokens) {
            result = result.add(token);
        }

        return result;
    }
}
